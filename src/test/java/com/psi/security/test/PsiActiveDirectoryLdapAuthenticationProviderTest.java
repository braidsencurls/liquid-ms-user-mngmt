package com.psi.security.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.ldap.core.DirContextOperations;

import com.psi.security.PsiActiveDirectoryLdapAuthenticationProvider;


public class PsiActiveDirectoryLdapAuthenticationProviderTest {
	
	@Test
	public void doAuthenticationTest() {
		
		PsiActiveDirectoryLdapAuthenticationProvider ldapProvider = 
				new PsiActiveDirectoryLdapAuthenticationProvider("incontrol-tech.local", "ldap://10.9.80.114:389",
						"dc=incontrol-tech,dc=local", "incontrol-tech\\", false);
		DirContextOperations e = ldapProvider.doAuthentication("kul_grace", "psip@ssw0rd");
		assertNotNull(e);
		
	}

}
