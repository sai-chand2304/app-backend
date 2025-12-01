package com.auth.security;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.dto.UserDto;
import com.auth.models.User;
import com.auth.repos.UserRepository;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Data
@ToString
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserDto user=null;
		
		try{
			User userFromDb = userRepo.findByEmail(email);
			
			System.err.println("Role from db : "+userFromDb.getRole());
			
			user = mapper.map(userFromDb, UserDto.class);
		}
		catch(Exception e) {
			System.err.println(e.getClass().getName());
			System.err.println("exception while fetching the user");
		}
	
		if(user==null)
		{
			System.err.println("user not found");
			throw new UsernameNotFoundException("user not found for the username");
		}
		
		log.info("Got the User From the Employee Service username : {} & password {}",user.getEmail(),user.getPassword());
		return new CustomUserDetails(user);
	}

}
