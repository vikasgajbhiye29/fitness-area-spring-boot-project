package com.fitnessarea.configuration;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fitnessarea.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	// requirement :
	public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;

	// public static final long JWT_TOKEN_VALIDITY = 60;
	private String secret = "52c79a5d9e0777727d7f60e8b6eafaec64230759aba2484c767c4e2fc63561a5";

	// retrieve username from jwt token
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	// validate token
		public Boolean isValid(String token, UserDetails userDetails) {
			String username = extractUsername(token);
			return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
		}

	// retrieve expiration date from jwt token
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}

	// for retrieveing any information from token we will need the secret key
	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSiningKey()).build().parseSignedClaims(token).getPayload();

	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		Date expiration = extractExpiration(token);
		return expiration.before(new Date());
	}

//	 generate token for user
//	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> claims = new HashMap<>();
//		return generateToken(claims, userDetails.getUsername());
//	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	public String generateToken(User user) {
		return Jwts.builder().subject(user.getUsername()).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).signWith(getSiningKey())
				.compact();
	}

	private SecretKey getSiningKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	

}
