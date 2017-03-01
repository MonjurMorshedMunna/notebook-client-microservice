package com.notebook.repositories;

import com.notebook.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by munna on 2/24/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
