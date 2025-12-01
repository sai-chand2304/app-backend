package com.auth.dto;



import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.auth.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class UserDto {


		@JsonProperty(access = Access.READ_ONLY)
	    private Long id;

	    @NotBlank(message = "Name is required")
	    private String name;

	    @Email(message = "Invalid email format")
	    @NotBlank(message = "Email is required")
	    private String email;
	    
	    @JsonProperty(access = Access.READ_ONLY)
	    private Role role;

	    @NotBlank(message = "Password is required")
	    private String password;

		@JsonProperty(access = Access.READ_ONLY)
	    private LocalDate registeredDate;
}
