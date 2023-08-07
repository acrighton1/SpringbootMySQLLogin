package com.aaroncrighton.eudatabase.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Indicates that this class is a configuration class
@EnableWebSecurity //Enables Spring Security's web security support
public class SpringSecurity {

    @Bean //Defines a bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //Returns an instance of BCryptPasswordEncoder for encoding passwords
    }

    @Bean //Defines a bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) //Disables CSRF protection
                .authorizeHttpRequests((requests) -> requests                        
                        .requestMatchers("/registration/**").permitAll() //Permits all requests to the /registration/** endpoint
                        .requestMatchers("/login/**").permitAll() //Permits all requests to the /login/** endpoint
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") //Restricts access to the /user/** endpoint based on the user's role
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN") //Restricts access to the /admin/** endpoint based on the user's role
                        .anyRequest().authenticated() //Requires authentication for all other requests
                )
                .formLogin((form) -> form
                        .loginPage("/login") //Sets the login page URL
                        .loginProcessingUrl("/login") //Sets the login processing URL
                        .defaultSuccessUrl("/user/") //Sets the default success URL after successful login
                        .permitAll() //Permits all users to access the login page and processing URL                        
                        
                )
             .logout((logout) -> logout
            .logoutSuccessUrl("/index.html") //Sets the logout success URL
            .permitAll() //Permits all users to logout
        )
        .exceptionHandling((exceptionHandling) ->
         exceptionHandling
             .accessDeniedPage("/errors/access-denied") //Sets the access denied page URL for handling access denied exceptions
         );
        return http.build(); //Builds and returns the security filter chain
    }
}