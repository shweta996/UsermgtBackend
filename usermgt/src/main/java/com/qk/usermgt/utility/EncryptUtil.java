package com.qk.usermgt.utility;

import com.qk.usermgt.dto.LoginDto;
import com.qk.usermgt.model.User;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Component;

@Component
public class EncryptUtil {

	public String encryptPassword(String password)
	{
		return new BCryptPasswordEncoder().encode(password);	
	}
	
	public boolean isPassword(LoginDto login, User user)
	{
		return new BCryptPasswordEncoder().matches(login.getPassword(),user.getPassword());
	}

}
