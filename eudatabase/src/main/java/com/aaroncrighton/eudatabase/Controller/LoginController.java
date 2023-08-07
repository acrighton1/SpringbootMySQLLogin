package com.aaroncrighton.eudatabase.Controller;

// Importing necessary classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aaroncrighton.eudatabase.DTO.UserDto;
import com.aaroncrighton.eudatabase.Model.User;
import com.aaroncrighton.eudatabase.Service.UserService;

import jakarta.validation.Valid;

// Annotation to indicate that this is a Controller class
@Controller
public class LoginController {

    // Autowiring the UserService
    @Autowired
    private UserService userService;

    // Mapping the "/login" URL to this method
    @RequestMapping("/login")
    public String loginForm() {
        // Returning the name of the view to display for the login form
        return "login";
    }

    // Mapping the "/registration" URL to this method using a GET request
    @GetMapping("/registration")
    public String registrationForm(Model model) {
        // Creating a new UserDto object
        UserDto user = new UserDto();
        // Adding the UserDto object to the model
        model.addAttribute("user", user);
        // Returning the name of the view to display for the registration form
        return "registration";
    }

    // Mapping the "/registration" URL to this method using a POST request
    @PostMapping("/registration")
    public String registration(
            // Validating the UserDto object and binding the result to the BindingResult object
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        // Finding an existing user by their email
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        // If an existing user is found, reject the email field with an error message
        if (existingUser != null)
            result.rejectValue("email", null,
                    "User already registered !!!");

        // If there are any validation errors, add the UserDto object to the model and return the registration view
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/registration";
        }

        // If there are no validation errors, save the user and redirect to the registration page with a success message
        userService.saveUser(userDto);
        return "redirect:/registration?success";
    }
}


//This code is a Java class named LoginController that is annotated with @Controller, indicating that it is a Spring MVC Controller class. It has three methods: loginForm(), registrationForm(Model model), and registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model).

//The loginForm() method is mapped to the /login URL and returns the name of the view to display for the login form.

//The registrationForm(Model model) method is mapped to the /registration URL using a GET request. It creates a new UserDto object, adds it to the model, and returns the name of the view to display for the registration form.

//he registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) method is mapped to the /registration URL using a POST request. It validates the UserDto object and checks if there is an existing user with the same email. If there is, it rejects the email field with an error message. If there are any validation errors, it returns the registration view. If there are no validation errors, it saves the user and redirects to the registration page with a success message.