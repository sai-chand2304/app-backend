package com.auth.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class JwtUtil {
	
	private String jwtSecret="1uORMccTFcTWQvjupAGxGapKFtcSRb4IPAaUJpfxbQoXuHutABXbNdt74yAg4SvofyvqSY6MXcbwoTXp7gk2Q3jIjOjsaUAUz4xA+7OHU4w=";
	
	public String generateJwtToken(String username, CustomUserDetails userDto) {
	    return Jwts.builder()
	            .setSubject(userDto.getEmail())
	            .claim("userId", userDto.getUserId())
	            .claim("role", userDto.getRole())
	            .setIssuedAt(new Date())
	            .setExpiration(new Date((new Date()).getTime() + 864000000)) 
	            .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes(StandardCharsets.UTF_8))
	            .compact();
	}
	
	
	 public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) 
	 {
	  String authString = (String) claims.get("role"); 
	  
	  List<GrantedAuthority>authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+authString);
	  
	  authorities.forEach(System.err::println);
	  
	  return authorities; 
	 }
	 
	
	  public Claims validateJwtToken(String token) {
	        try {
	        	System.err.println("validating the jwt!!!");
	            return Jwts.parserBuilder()
	                       .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
	                       .build()
	                       .parseClaimsJws(token)
	                       .getBody();
	        }
	        
	        catch (Exception e) {
	        	System.out.println("Token validation failed "+e.getMessage());
	            throw new RuntimeException(e.getMessage());
	        	
	        }
	    }

	
	

}
