package com.qk.usermgt.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.qk.usermgt.dto.AuthenticationDto;
import com.qk.usermgt.dto.LoginDto;
import com.qk.usermgt.dto.ResetPasswordDto;
import com.qk.usermgt.dto.UserDto;
import com.qk.usermgt.model.User;
import com.qk.usermgt.response.Response;
import com.qk.usermgt.service.UserService;
import com.qk.usermgt.service.UserServiceImpl;
import com.qk.usermgt.utility.CommonFiles;
import com.qk.usermgt.utility.TokenGenerator;
import com.qk.usermgt.utility.Utility;

@RestController
@RequestMapping("/usermanagement")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private 
	TokenGenerator tokenGenerator;
	
	@Autowired
	Utility utility;
	
	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDto login, HttpServletResponse servletResponse) {
		String token = userService.login(login);
		servletResponse.setHeader("Authorization", token);
		Response response = new Response(200, "logged in successfully", token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PutMapping("/logout")
	public ResponseEntity<Response> logout(@RequestHeader String token) {
		String token1 = userService.logout(token);
		Response response = new Response(200, "logged out",token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@GetMapping("/forget")
	public ResponseEntity<Response> forgetPassword(@RequestParam String email, HttpServletRequest request) {
		StringBuffer resetUrl = request.getRequestURL();
		String message = userService.forgotPassword(email, resetUrl);
		Response response = new Response(200, message, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("/reset")
	public ResponseEntity<Response> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto, @RequestHeader String token) {
		String message = userService.resetPassword(resetPasswordDto, token);
		Response response = new Response(200, message, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<Response> registerUser(@RequestBody String registerJson, @RequestParam MultipartFile profileImage)  throws JsonMappingException, JsonProcessingException,IOException {
		UserDto userDto = new UserDto();
		String message = userService.registerUser(userDto, profileImage);
		userDto = (UserDto) utility.mapper(registerJson, userDto);
		System.out.println("User dto received after conversion "+userDto);
		System.out.println("Profile Image "+profileImage);
		Response response = new Response(200,message,null);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Response> editUser(@RequestHeader String token, @RequestParam String editJson, @RequestParam MultipartFile profileImage ) throws IOException { 
		UserDto userDto = new UserDto();
		String id = tokenGenerator.verifyToken(token);
		String message =userService.editUser(id, userDto, profileImage);
		userDto = (UserDto) utility.mapper(editJson, userDto);
		System.out.println("User dto received after conversion"+userDto);
		Response response = new Response(200,message,null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getallusers")
	public List<User> getAllNotes(@RequestHeader String token) {
		List<User> users = userService.getUsers(token);
		return users;
	}
	
	@GetMapping("/getcountries")
	public Map<String,Integer> getallcountries(){
		Map<String,Integer> countryMap = userService.getCountries();
		return countryMap;	
	}
	
	@GetMapping("/getregisteredusers")
	public List<User> getRegisteredUsers(@RequestHeader String token) {
	  List<User> registredUsers = userService.getRegisteredUsers(token);
	  return registredUsers;		
	}
	
	@DeleteMapping("/removeprofile")
	public ResponseEntity<Response> removeProfilePicture(String token) {		
		String id = tokenGenerator.verifyToken(token);
		String message = userService.removeProfilePicture(id);
		Response response = new Response(200,message,null);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getprofilePic")
	public ResponseEntity<Response> getProfilePic(@RequestHeader String token) throws IOException {
		String id = tokenGenerator.verifyToken(token);
		String message = userService.getProfilePicture(id);
		Response response = new Response(200,message,null);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateprofilePic")
	public ResponseEntity<Response> updateProfilePic(@RequestHeader String token, @RequestParam MultipartFile profileImage ) throws IOException {
		String id = tokenGenerator.verifyToken(token);
		String message = userService.updateProfilePic(id,profileImage);
		Response response = new Response(200,message,null);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PutMapping("/authentication")
	public ResponseEntity<Response> updateAuthentication(@RequestHeader String token, @RequestBody AuthenticationDto authentication) {
		String id = tokenGenerator.verifyToken(token);
		String message = userService.updateAuthentication(id, authentication);
		Response response = new Response(200,message,null);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
}



