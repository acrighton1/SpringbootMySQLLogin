package com.aaroncrighton.eudatabase.Controller;

// Importing necessary classes
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Annotation to indicate that this is a Controller class
@Controller
// Mapping all URLs starting with "/user/" to this controller
@RequestMapping("/user/")
public class UserController {
    // Mapping the "/user/" URL to this method using a GET request
    @GetMapping("/")
    public String registrationForm() {
        // Returning the name of the view to display for the user page
        return "user";
    }
    
}