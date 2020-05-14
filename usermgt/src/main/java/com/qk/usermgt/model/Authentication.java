package com.qk.usermgt.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Authentication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authenticationId;
	
	private boolean rememberMe = true;
	
	private boolean forgotPassword = true;
	
	private String name = "User Management";

	public Authentication() {}
	
	public Authentication(Long authenticationId, boolean rememberMe, boolean forgotPassword, String name) {
		
		this.authenticationId = authenticationId;
		this.rememberMe = rememberMe;
		this.forgotPassword = forgotPassword;
		this.name = name;
	}

	public Long getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(Long authenticationId) {
		this.authenticationId = authenticationId;
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
		return "Authentication [authenticationId=" + authenticationId + ", rememberMe=" + rememberMe
				+ ", forgotPassword=" + forgotPassword + ", name=" + name + "]";
	}
	
	

}
