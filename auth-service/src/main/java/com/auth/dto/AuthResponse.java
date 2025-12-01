package com.auth.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.auth.enums.Role;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("prototype")
public class AuthResponse {

	private String token;
	
    private Role role;
}