package com.auth.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth.dto.UserDto;
import com.auth.enums.Role;

import lombok.Data;
import lombok.ToString;



@Component
@ToString
@Data
public class CustomUserDetails implements UserDetails{
	
	@Autowired
	private UserDto user;
	
	private String email;
	private String role;
	private String userId;
 
	
	public CustomUserDetails(UserDto user) {
		this.user = user;
	}
	

	public CustomUserDetails() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// class : SimpleGrantedAuthority --> GrantedAuthority
		return List.of(new SimpleGrantedAuthority(user.getRole().toString()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}
	
	
	public Role getRole() {
		// TODO Auto-generated method stub
		return user.getRole();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Long getUserId() {
		return user.getId();
	}
	
	public String getEmail() {
		return user.getEmail();
	}
}