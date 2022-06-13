package com.cleber.helpDeskapi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);

			if(claims != null) {
				String userName = claims.getSubject();
				Date experationDate = claims.getExpiration();
				Date now = new Date(System.currentTimeMillis());
				
				if(userName != null && experationDate != null && now.before(experationDate)) {
					return true;
				}
			}
		return false;
	}

	private Claims getClaims(String token) {
	
				try {
					return Jwts.parser()
							.setSigningKey(secret.getBytes())
							.parseClaimsJws(token).getBody();
				} catch (Exception e) {
					return null;
				}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}


}
