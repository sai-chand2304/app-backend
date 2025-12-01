package com.auth.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.dto.AuthRequest;
import com.auth.dto.AuthResponse;
import com.auth.dto.UserDto;
import com.auth.models.User;
import com.auth.services.AuthService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/my-app")
public class AuthController {

    @Autowired
    private AuthService authService;
  
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDto user) {
      log.info(user.getEmail());
      User users=  authService.createUser(user);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest);
        return ResponseEntity.ok(response);
    }

//	@PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/validate")
//    public ResponseEntity<String> validateToken(@RequestParam String token) {
//        //authService.validateToken(token);
//        return ResponseEntity.ok("Token is valid");
//    }
}
