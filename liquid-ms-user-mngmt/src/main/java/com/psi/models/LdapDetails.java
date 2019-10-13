package com.psi.models;

public class LdapDetails {
	
	private String domain;
	
	private String url;
	
	private String rootDn;
	
	private String sAMAccountPrefixLogin;
	
	private String useUserDatabase;
	
	private String searchSecurityPrincipal;
	
	private String searchCredentials;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRootDn() {
		return rootDn;
	}

	public void setRootDn(String rootDn) {
		this.rootDn = rootDn;
	}

	public String getsAMAccountPrefixLogin() {
		return sAMAccountPrefixLogin;
	}

	public void setsAMAccountPrefinLogin(String sAMAccountPrefixLogin) {
		this.sAMAccountPrefixLogin = sAMAccountPrefixLogin;
	}

	public String getUseUserDatabase() {
		return useUserDatabase;
	}

	public void setUseUserDatabase(String useUserDatabase) {
		this.useUserDatabase = useUserDatabase;
	}

	@Override
	public String toString() {
		return "LdapDetails [domain=" + domain + ", url=" + url + ", rootDn=" + rootDn + ", sAMAccountPrefinLogin="
				+ sAMAccountPrefixLogin + ", useUserDatabase=" + useUserDatabase + "]";
	}

	public String getSearchSecurityPrincipal() {
		return searchSecurityPrincipal;
	}

	public void setSearchSecurityPrincipal(String searchSecurityPrincipal) {
		this.searchSecurityPrincipal = searchSecurityPrincipal;
	}

	public String getSearchCredentials() {
		return searchCredentials;
	}

	public void setSearchCredentials(String searchCredentials) {
		this.searchCredentials = searchCredentials;
	}
}
