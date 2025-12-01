package com.auth.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException 
    {
       String header = request.getHeader("Authorization");
       
       
       String path = request.getRequestURI();

       if(path.startsWith("/api/my-app/login") ||
    	       path.startsWith("/api/my-app/register") ||
    	       path.startsWith("/v3/api-docs") ||
    	       path.startsWith("/swagger-ui") ||
    	       path.startsWith("/swagger-resources") ||
    	       path.startsWith("/swagger") ||
    	       path.startsWith("/webjars") ||
    	       path.startsWith("/actuator")) {
    	        filterChain.doFilter(request, response);
    	        return;
    	    }
       
       if(header!=null &&  header.startsWith("Bearer"))
       {
    	   String token =header.substring(7);
    	   
    	   Claims claims = jwtUtil.validateJwtToken(token);
    	   
    	   if(claims==null)
    	   {
    		   throw new RuntimeException("token validation failed");
    	   }
    	   
    	   List<GrantedAuthority> authorities = jwtUtil.getAuthoritiesFromClaims(claims);
    	   
    	   UserDetails userDetails= new User(claims.getSubject(),"",authorities);
    	   System.err.println("the authorities are : "+authorities);
    	   SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,null,authorities));
    	   System.err.println("set the authentication instance in security context");
    	   
       }
    	
    	
        filterChain.doFilter(request, response);
    }
}

