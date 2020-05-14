package com.qk.usermgt.dto;

import javax.validation.constraints.NotEmpty;

public class LoginDto {
    
	@NotEmpty(message="Please enter the username")
	private String username;
	@NotEmpty(message="Please enter the password")
	private String password;
	
	
	public LoginDto()
	{
		
	}
	public LoginDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDto [username=" + username + ", password=" + password + "]";
	}
	

}
