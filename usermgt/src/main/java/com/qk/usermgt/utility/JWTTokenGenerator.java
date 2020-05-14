package com.qk.usermgt.utility;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenGenerator implements TokenGenerator{
	String secret_token="shk29969999";
	
	@Override
	public String generateToken(String id) {
		String token=Jwts.builder()
				.setSubject("userManagement")
				.setExpiration(new Date(System.currentTimeMillis()+10000000*1000))
				.setId(id)
				.signWith(SignatureAlgorithm.HS256, secret_token)
				.compact();
	return token;
		
	}

	@Override
	public String verifyToken(String token) {
		Jws<Claims> claims = Jwts.parser()
				  .setSigningKey(secret_token)
				  .parseClaimsJws(token);
				String id = claims.getBody().getId();
				return id;
	
	}
    

}
