package com.notebook.repositories;

import com.notebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by munna on 2/24/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
