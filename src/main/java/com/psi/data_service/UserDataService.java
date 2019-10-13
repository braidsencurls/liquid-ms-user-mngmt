package com.psi.data_service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.stereotype.Service;

import com.psi.constants.CollectionConstants;
import com.psi.models.ADPrincipalDetails;
import com.psi.models.Authentication;
import com.psi.models.AuthenticationRequest;
import com.psi.models.LdapDetails;
import com.psi.models.User;
import com.psi.persistence.MongoBasicCrud;
import com.psi.security.PsiActiveDirectoryLdapAuthenticationProvider;

@Service
public class UserDataService {
	
	private static Logger logger = LoggerFactory.getLogger(UserDataService.class);

	@Autowired
	MongoBasicCrud mongoCrud;
	
	@Autowired
	ActiveDirectoryService adService;
	
	public User createUser(User user) throws PersistenceException, InvalidParameterException, Exception {
		logger.info("User: " + user);
		mongoCrud.insert(user, CollectionConstants.COL_USERS);
		return user;
	}
	
	public User searchUserByUsername(String username) {
		return (User) mongoCrud.find("{ username : { $eq : '" + username + "' } }", CollectionConstants.COL_USERS, User.class);
	}
	
	public List<User> fetchAllUsers() {
		return (List<User>) mongoCrud.findAll(User.class, CollectionConstants.COL_USERS);
	}
	
	public long updateUser(User user) throws Exception {
		Map<String, Object> newValue = new HashMap<String, Object>();
		Field[] fields = user.getClass().getDeclaredFields();
		List<Field> fieldList = Arrays.asList(fields);
		for (Field f : fieldList) {
			try {
				if(!f.getName().equals("_id")) {
					PropertyDescriptor objPropertyDescriptor = new PropertyDescriptor(f.getName(), user.getClass());
					Object variableValue = objPropertyDescriptor.getReadMethod().invoke(user);
					newValue.put(f.getName(), variableValue);
				}
			} catch (Exception e) {
				throw new Exception();
			}
		}
		return mongoCrud.update("{'username':'" + user.getUsername() + "'}", newValue, User.class);
	}
	
	public long removeUser(User user) {
		logger.info("Deleting username: " + user.getUsername());
		return mongoCrud.remove("{'username':'" + user.getUsername() + "'}", CollectionConstants.COL_USERS);
	}
	
	public boolean authenticate(AuthenticationRequest request) {
		LdapDetails ldap = request.getLdapDetails();
		Authentication auth = request.getAuthentication();
		PsiActiveDirectoryLdapAuthenticationProvider ldapProvider = new PsiActiveDirectoryLdapAuthenticationProvider(
				ldap.getDomain(), ldap.getUrl(), ldap.getRootDn(), ldap.getsAMAccountPrefixLogin(),
				Boolean.valueOf(ldap.getUseUserDatabase()));
		DirContextOperations e = ldapProvider.doAuthentication(auth.getUsername(), auth.getPassword());
		if (e != null) {
			return true;
		}

		return false;
	}
	
	public ADPrincipalDetails searchFromAD(AuthenticationRequest request) {
		return  adService.searchUser(request);
	}

}
