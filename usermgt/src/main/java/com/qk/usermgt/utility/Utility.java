package com.qk.usermgt.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qk.usermgt.dto.UserDto;

@Component
public class Utility {
	
static ObjectMapper objectMapper;
	
	static {
		objectMapper = new ObjectMapper();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	public Object mapper(String userJson, UserDto userDto) throws JsonMappingException, JsonProcessingException {
		Object object = objectMapper.readValue(userJson, userDto.getClass()); 
		
		return object;
	}

}
