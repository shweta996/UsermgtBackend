package com.qk.usermgt.utility;

public interface TokenGenerator {
    String generateToken(String id);
	
	String verifyToken(String token);

}
