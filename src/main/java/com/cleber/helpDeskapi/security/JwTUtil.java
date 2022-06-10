package com.cleber.helpDeskapi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwTUtil {

	@Value("${jwt.expiration}")
	private String expiration;
	@Value("${jwt.secret}")
	private String secret;
	
	public String generationToken(String email) {
		return Jwts.builder().setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}


}
