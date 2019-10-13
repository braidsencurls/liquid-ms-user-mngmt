package com.psi.data_service;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.psi.models.ADPrincipalDetails;
import com.psi.models.Authentication;
import com.psi.models.AuthenticationRequest;
import com.psi.models.LdapDetails;

@Service
public class ActiveDirectoryService {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private DirContext getDirectoryContext(LdapDetails ldap) {
		Hashtable env = new Hashtable();

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldap.getUrl());
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, ldap.getSearchSecurityPrincipal());
		env.put(Context.SECURITY_CREDENTIALS, ldap.getSearchCredentials());

		try {
			return new InitialLdapContext(env, null);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public ADPrincipalDetails searchUser(AuthenticationRequest request) {
		ADPrincipalDetails details = new ADPrincipalDetails();
		try {
			LdapDetails ldap = request.getLdapDetails();
			Authentication auth = request.getAuthentication();
			if (StringUtils.isNullOrEmpty(auth.getUsername())) {
				return null;
			}
			NamingEnumeration<SearchResult> results = null;
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			controls.setCountLimit(1);
			controls.setTimeLimit(500000);

			String searchString = "(&(objectClass=*)(sAMAccountName=" + auth.getUsername() + "))";
			results = getDirectoryContext(ldap).search(ldap.getRootDn(), searchString, controls);
			
			if (results == null) {
				return null;
			}

			if (results != null && results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				if (attributes.get("cn") != null) {
					details.setCn(attributes.get("cn").toString().replace("cn:", ""));
				}

				if (attributes.get("mail") != null) {
					details.setEmail(attributes.get("mail").toString().replace("mail:", ""));
				}

				if (attributes.get("givenName") != null) {
					details.setGivenName(attributes.get("givenName").toString().replace("givenName:", ""));
				}

				if (attributes.get("sn") != null) {
					details.setSn(attributes.get("sn").toString().replace("sn:", ""));
				}

				if (attributes.get("telephoneNumber") != null) {
					details.setMobileNo(attributes.get("telephoneNumber").toString().replace("telephoneNumber:", ""));
				}

				if (attributes.get("sAMAccountName") != null) {
					details.setUserName(attributes.get("sAMAccountName").toString().replace("sAMAccountName:", ""));
				}

				if (attributes.get("userPrincipalName") != null) {
					details.setUserPrincipalName(
							attributes.get("userPrincipalName").toString().replace("userPrincipalName:", ""));
				}

				if (attributes.get("password") != null) {
					details.setUserPassword(attributes.get("password").toString().replace("password:", ""));
				}

				if (attributes.get("ou") != null) {
					details.setOu(attributes.get("ou").toString().replace("ou:", ""));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return details;
	}

}
