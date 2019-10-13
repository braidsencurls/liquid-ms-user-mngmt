package com.psi.models;

public class ADPrincipalDetails {
	
	private String userName;
	
    private String cn;
    
    private String sn;
    
    private String email;
    
    private String title;
    
    private String givenName;
    
    private String mobileNo;
    
    private String userPassword;
    
    private String userPrincipalName;
    
    private String userAccountControl;
    
    private String ou;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getUserAccountControl() {
		return userAccountControl;
	}

	public void setUserAccountControl(String userAccountControl) {
		this.userAccountControl = userAccountControl;
	}

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	@Override
	public String toString() {
		return "ADPrincipalDetails [userName=" + userName + ", cn=" + cn + ", sn=" + sn + ", email=" + email
				+ ", title=" + title + ", givenName=" + givenName + ", mobileNo=" + mobileNo + ", userPassword="
				+ userPassword + ", userPrincipalName=" + userPrincipalName + ", userAccountControl="
				+ userAccountControl + ", ou=" + ou + "]";
	}

}
