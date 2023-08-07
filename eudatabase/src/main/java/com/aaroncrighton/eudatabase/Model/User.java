package com.aaroncrighton.eudatabase.Model;

// Importing necessary classes
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Using Lombok annotations to generate getters, setters, and constructors
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// Annotation to indicate that this is an Entity class
@Entity
// Setting the table name for this entity
@Table(name = "users")
public class User {

    // Defining the id field as the primary key and setting the generation strategy to IDENTITY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Defining the name field as non-nullable
    @Column(nullable = false)
    private String name;
    // Defining the email field as non-nullable and unique
    @Column(nullable = false, unique = true)
    private String email;
    // Defining the password field as non-nullable
    @Column(nullable = false)
    private String password;
    // Defining a many-to-many relationship with the Role entity
    // Setting the fetch type to EAGER and cascading all operations
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // Setting up the join table for the many-to-many relationship
    @JoinTable(
        name = "users_roles",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();

    // Constructor to create a new User with a name, email, password, and list of roles
    public User(String name, String email, String password, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
 
}