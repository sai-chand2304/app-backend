package com.auth.services;

import com.auth.dto.AuthRequest;
import com.auth.dto.AuthResponse;
import com.auth.dto.UserDto;
import com.auth.models.User;

import jakarta.validation.Valid;

public interface AuthService {
	User createUser(UserDto userDto);
	AuthResponse login(@Valid AuthRequest authRequest);
}
