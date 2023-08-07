package com.aaroncrighton.eudatabase.Service;

// Importing necessary classes
import com.aaroncrighton.eudatabase.DTO.UserDto;
import com.aaroncrighton.eudatabase.Model.User;

// Interface for the UserService
public interface UserService {
    // Method to save a user to the database
    void saveUser(UserDto userDto);

    // Method to find a user by their email
    User findUserByEmail(String email);
}