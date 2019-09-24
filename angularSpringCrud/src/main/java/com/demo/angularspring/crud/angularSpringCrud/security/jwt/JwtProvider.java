package com.demo.angularspring.crud.angularSpringCrud.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.demo.angularspring.crud.angularSpringCrud.security.service.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${springCrud.app.jwtSecret}")
	private String jwtSecret;
	
	@Value("${springCrud.app.jwtExpiration}")
	private int jwtExpirations;
	
	
	public String genarateJwtToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
		
		
		return Jwts.builder()
                 .setSubject((userPrinciple.getUsername()))
                 .setIssuedAt(new Date())
                 .setExpiration(new Date((new Date()).getTime() + jwtExpirations*1000))
                 .signWith(convertToKey(this.jwtSecret), SignatureAlgorithm.HS256)
                 .compact();	
	}
	
	public boolean validateJwtToken(String authToken) {
        try {
        	System.out.println("----------------------------------------------------");
        	System.out.println("----------------------------------------------------");
        	System.out.println("----------------------------------------------------");
            Jwts.parser().setSigningKey(convertToKey(this.jwtSecret)).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }
	
	private Key convertToKey(String secretKey) {
		byte [] secretKeyByte = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(secretKeyByte);
	}
	
	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody().getSubject();
	}
	
}
