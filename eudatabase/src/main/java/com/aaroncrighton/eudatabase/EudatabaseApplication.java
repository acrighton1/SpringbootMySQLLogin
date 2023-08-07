package com.aaroncrighton.eudatabase;

// Importing necessary classes for the Spring Boot application
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// Annotation to indicate that this is a Spring Boot application
// Excluding certain auto-configuration classes related to security
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class EudatabaseApplication {

    // Main method to run the Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(EudatabaseApplication.class, args);
    }

}