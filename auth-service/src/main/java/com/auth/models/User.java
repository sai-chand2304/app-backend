package com.auth.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

import com.auth.enums.Role;


@Entity
@Table( name = "users",
		uniqueConstraints = {	
    @UniqueConstraint(columnNames = "email")
    
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;
    
    
    @NotBlank(message = "Password is required")
    private String password;


    @NotNull(message = "Mention Role")
    @Enumerated(EnumType.STRING)
    private Role role;


    private LocalDate registeredDate;
    
    @PrePersist
    public void prePersist() {
        this.registeredDate = LocalDate.now();
    }
}
