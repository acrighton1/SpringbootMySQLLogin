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
@NoArgsConstructor
@AllArgsConstructor
// Annotation to indicate that this is an Entity class
@Entity
// Setting the table name for this entity
@Table(name = "roles")
public class Role {
    // Defining the id field as the primary key and setting the generation strategy to IDENTITY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Defining the name field as non-nullable and unique
    @Column(nullable = false, unique = true)
    private String name;
    // Defining a many-to-many relationship with the User entity
    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    // Constructor to create a new Role with only a name
    public Role(String name) {
        this.name = name;
    }
}