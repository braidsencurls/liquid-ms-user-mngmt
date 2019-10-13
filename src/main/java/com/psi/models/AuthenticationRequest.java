package com.psi.models;

public class AuthenticationRequest {
	
	private LdapDetails ldapDetails;
	
	private Authentication authentication;

	public LdapDetails getLdapDetails() {
		return ldapDetails;
	}

	public void setLdapDetails(LdapDetails ldapDetails) {
		this.ldapDetails = ldapDetails;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	@Override
	public String toString() {
		return "AuthenticationRequest [ldapDetails=" + ldapDetails + ", authentication=" + authentication + "]";
	}

}
