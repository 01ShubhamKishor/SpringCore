package com.WebApplication.ChatApplication.Config;
import java.util.Date;

import javax.crypto.SecretKey;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
	
	private static SecretKey key=Keys.hmacShaKeyFor(JwtConstent.SECREAT_KEY.getBytes());
	
	//Token Creation
	public static String generateToken(org.springframework.security.core.Authentication auth)
	{
		String jwt=Jwts.builder()
		.setIssuer("Shubham")
		.setIssuedAt(new Date())
		.setExpiration(new Date(new Date().getTime()+86400000))
        .claim("email",auth.getName())
        .signWith(key)
        .compact();
		
		return jwt;
		
	}
	
	public static String getEmailFromJwtToken(String jwt)
	{
		// Bearer token 
		jwt=jwt.substring(7);
		
		Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
			
		String email=String.valueOf(claims.get("email"));
		return email;
		
	}
	

}
