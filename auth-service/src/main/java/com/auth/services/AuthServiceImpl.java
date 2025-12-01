package com.auth.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.dto.AuthRequest;
import com.auth.dto.AuthResponse;
import com.auth.dto.UserDto;
import com.auth.enums.Role;
import com.auth.models.User;
import com.auth.repos.UserRepository;
import com.auth.security.CustomUserDetails;
import com.auth.security.JwtUtil;



@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	AuthResponse authResponse;

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AuthenticationManager mgr;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
    private ModelMapper mapper;

	@Override
	public User createUser(UserDto userDto) 
	{
		User user = mapper.map(userDto, User.class);
		System.err.println(user);
		
		if(userDto.getName().equalsIgnoreCase("sai")) {
			user.setRole(Role.ADMIN);
		}
		else{
			user.setRole(Role.USER);

		}
		
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
    	 return   userRepository.save(user);		
	}

	@Override
	public AuthResponse login(AuthRequest authRequest) {

		String email = authRequest.getEmail();
		String password = authRequest.getPassword();
		System.err.println("got email and password : "+email+" "+password);
		
		Authentication  auth = mgr.authenticate(new UsernamePasswordAuthenticationToken(email,password));
		
		CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
		System.err.println("Authentication sucess!");
		System.err.println("Printing pincipal : "+customUserDetails.getEmail());
		
		String jwtToken = jwtUtil.generateJwtToken(email, customUserDetails);
		
		AuthResponse authResp=new AuthResponse();
		authResp.setToken(jwtToken);
		authResp.setRole(customUserDetails.getRole());
		
		return authResp ;
	}

	
	
}
