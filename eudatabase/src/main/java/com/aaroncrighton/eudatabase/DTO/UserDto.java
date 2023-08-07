package com.aaroncrighton.eudatabase.DTO;

// Importing necessary classes
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Using Lombok annotations to generate getters, setters, and constructors
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    // Field to store the user's id
    private Long id;

    // Field to store the user's name
    // Adding validation constraints to ensure that the name is not empty
    @NotEmpty(message = "Please enter valid name.")
    private String name;

    // Field to store the user's email
    // Adding validation constraints to ensure that the email is not empty and is a valid email address
    @NotEmpty(message = "Please enter valid email.")
    @Email
    private String email;

    // Field to store the user's password
    // Adding validation constraints to ensure that the password is not empty
    @NotEmpty(message = "Please enter valid password.")
    private String password;
}