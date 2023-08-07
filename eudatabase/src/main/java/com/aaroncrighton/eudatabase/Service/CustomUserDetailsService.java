package com.aaroncrighton.eudatabase.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aaroncrighton.eudatabase.Model.User;
import com.aaroncrighton.eudatabase.Repository.UserRepository;

import java.util.stream.Collectors;

@Service
// This class implements the UserDetailsService interface to provide custom user authentication
public class CustomUserDetailsService implements UserDetailsService {

    // Autowiring the UserRepository to access user data from the database
    @Autowired
    private UserRepository userRepository;

    // Overriding the loadUserByUsername method to provide custom user authentication
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        // Finding the user by their email
        User user = userRepository.findByEmail(usernameOrEmail);
        if (user != null) { // If the user is found in the database
            // Create a new UserDetails object with the user's information
            return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // Set the username to the user's email
                user.getPassword(), // Set the password to the user's password
                user.getRoles().stream() // Get the user's roles
                    .map((role) -> new SimpleGrantedAuthority(role.getName())) // Map each role to a SimpleGrantedAuthority object
                    .collect(Collectors.toList()) // Collect the authorities into a list
            );
        } else {
            // If the user is not found, throw a UsernameNotFoundException
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}


