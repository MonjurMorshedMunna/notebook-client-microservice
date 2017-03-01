package com.notebook.repositories;

import com.notebook.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by munna on 2/24/17.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByUserEmail(String email);

}
