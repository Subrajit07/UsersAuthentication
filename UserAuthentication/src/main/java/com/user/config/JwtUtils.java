package com.user.config;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.user.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUtils {

	@Value("${jwt.secret}")
	private String securityKey;
	
	@Value("${jwt.expirationMS}")
	private Duration expireTokenTime;
	
	@Value("${jwt.refreshTokenMS}")
	private Duration refreshTokenTime;
	
	public String generateToken(Users user) {
		var now=new Date();
		var expireTime=new Date(now.getTime()+expireTokenTime.toMillis());
		return Jwts.builder()
								.setClaims(Map.of("username",user.getUsername(),
																	"userId",user.getId()))
								.setExpiration(expireTime)
								.setSubject(user.getUsername())
								.setIssuedAt(now)
								.signWith(getSignInKey(), SignatureAlgorithm.HS256)
								.compact();
	}
	
	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public boolean isTokenValid(String token, Users user) {
		String username = getUsernameFromToken(token);
		return username.equals(user.getUsername());
	}
	
	private Key getSignInKey() {
		byte[] decode = Decoders.BASE64.decode(securityKey);
		return Keys.hmacShaKeyFor(decode);
	}

}
