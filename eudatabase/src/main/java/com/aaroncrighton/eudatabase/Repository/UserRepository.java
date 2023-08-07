package com.aaroncrighton.eudatabase.Repository;

// Importing necessary classes
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aaroncrighton.eudatabase.Model.User;

// Annotation to indicate that this is a Repository class
@Repository
// This interface extends the JpaRepository interface for the User entity
public interface UserRepository extends JpaRepository<User, Long> {
    // Method to find a user by their email
    User findByEmail(String email);
}