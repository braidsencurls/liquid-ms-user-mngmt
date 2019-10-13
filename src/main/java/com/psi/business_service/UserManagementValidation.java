package com.psi.business_service;

import java.security.InvalidParameterException;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.psi.models.Authentication;
import com.psi.models.AuthenticationRequest;
import com.psi.models.LdapDetails;
import com.psi.models.User;

@Service
public class UserManagementValidation {
	
	public boolean isCreateUserValidParameters(User user) {
		if(!Strings.isNullOrEmpty(user.getLastname()) || !Strings.isNullOrEmpty(user.getEmail())) {
			return true;
		} else {
			throw new InvalidParameterException("Invalid Parameter/s");
		}
	}
	
	public boolean isAuthenticationValidParameters(AuthenticationRequest request) {
		Authentication auth = request.getAuthentication();
		LdapDetails ldap = request.getLdapDetails();
		
		if(!Strings.isNullOrEmpty(ldap.getUrl()) || !Strings.isNullOrEmpty(ldap.getDomain())) {
			if(!Strings.isNullOrEmpty(auth.getUsername()) || !Strings.isNullOrEmpty(auth.getPassword())) {
				return true;
			}
		}
		throw new InvalidParameterException("Invalid Parameter/s");
	}
}
