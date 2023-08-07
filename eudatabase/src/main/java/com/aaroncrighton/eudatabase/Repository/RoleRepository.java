package com.aaroncrighton.eudatabase.Repository;

// Importing necessary classes
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aaroncrighton.eudatabase.Model.Role;

// Annotation to indicate that this is a Repository class
@Repository
// This interface extends the JpaRepository interface for the Role entity
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Method to find a role by its name
    Role findByName(String name);
}