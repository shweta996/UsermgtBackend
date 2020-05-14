package com.qk.usermgt.dto;

public class AuthenticationDto {
	
    private boolean rememberMe;
	
	private boolean forgotPassword;
	
	private String name;
	
	public AuthenticationDto() {}

	public AuthenticationDto(boolean rememberMe, boolean forgotPassword, String name) {
		this.rememberMe = rememberMe;
		this.forgotPassword = forgotPassword;
		this.name = name;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public boolean isForgotPassword() {
		return forgotPassword;
	}

	public void setForgotPassword(boolean forgotPassword) {
		this.forgotPassword = forgotPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AuthenticationDto [rememberMe=" + rememberMe + ", forgotPassword=" + forgotPassword + ", name=" + name
				+ "]";
	}
	
}
