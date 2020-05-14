package com.qk.usermgt.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qk.usermgt.dto.AuthenticationDto;
import com.qk.usermgt.dto.LoginDto;
import com.qk.usermgt.dto.ResetPasswordDto;
import com.qk.usermgt.dto.UserDto;
import com.qk.usermgt.model.User;
import com.qk.usermgt.response.Response;

public interface UserService<country> {
	
    String login(LoginDto login);
	
	String logout(String token);
	
	String validateUser(String token);
	
	String forgotPassword(String email, StringBuffer requestUrl);
	
	String resetPassword(ResetPasswordDto resetPasswordDto,String token);
	
	String registerUser(UserDto userDto, MultipartFile profilePic) throws IOException;
	
	String editUser(String id, UserDto userDto, MultipartFile profilePic) throws IOException;

	List<User> getRegisteredUsers(String id);

	String updateAuthentication(String id, AuthenticationDto authentication);

	 List<User> getUsers(String id);

	Map<String, Integer> getCountries();

	User getUser(String id);

	String getProfilePicture(String id) throws IOException;

	String removeProfilePicture(String id);

	String updateProfilePic(String id, MultipartFile file) throws IOException;

}
