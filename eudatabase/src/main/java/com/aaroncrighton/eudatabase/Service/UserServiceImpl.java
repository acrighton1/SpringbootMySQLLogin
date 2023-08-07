package com.aaroncrighton.eudatabase.Service;

// Importing necessary classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import com.aaroncrighton.eudatabase.DTO.UserDto;
import com.aaroncrighton.eudatabase.Model.Role;
import com.aaroncrighton.eudatabase.Model.User;
import com.aaroncrighton.eudatabase.Repository.RoleRepository;
import com.aaroncrighton.eudatabase.Repository.UserRepository;
import com.aaroncrighton.eudatabase.Util.TbConstants;

// Annotation to indicate that this is a Service class
@Service
// This class implements the UserService interface
public class UserServiceImpl implements UserService {

    // Autowiring the necessary repositories and password encoder
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Overriding the saveUser method from the UserService interface
    @Override
    public void saveUser(UserDto userDto) {
        // Find the user role in the database
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        // If the role is not found, create and save a new role with the name "ROLE_USER"
        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        // Create a new User object with the information from the UserDto object
        // Encode the password using the password encoder
        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        // Save the user to the database
        userRepository.save(user);
    }

    // Overriding the findUserByEmail method from the UserService interface
    @Override
    public User findUserByEmail(String email) {
        // Find and return a user by their email using the UserRepository
        return userRepository.findByEmail(email);
    }
}



//This code consists of two classes: TbConstants and UserServiceImpl.

//TbConstants is a class that defines constants for the database table. It has a static interface named Roles that defines constants for user roles: USER and ADMIN.

//UserServiceImpl is a class that implements the UserService interface. It is annotated with @Service, indicating that it is a Spring Service class. It has three fields that are autowired: userRepository, roleRepository, and passwordEncoder.

//The saveUser(UserDto userDto) method finds the user role in the database using the roleRepository. If the role is not found, it creates and saves a new role with the name “ROLE_USER”. Then it creates a new User object with the information from the UserDto object, encodes the password using the passwordEncoder, and saves the user to the database using the userRepository.

//The findUserByEmail(String email) method finds and returns a user by their email using the userRepository.