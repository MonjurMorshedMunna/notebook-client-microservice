package com.notebook.services;

import com.notebook.entities.User;
import com.notebook.entities.UserRole;
import com.notebook.repositories.UserRepository;
import com.notebook.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by munna on 2/25/17.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserRole userRole = userRoleRepository.findByUserEmail(email);
        return new org.springframework.security.core.userdetails.User(userRole.getUser().getEmail(),
                userRole.getUser().getPassword(),
                AuthorityUtils.createAuthorityList(String.join(",", userRole.getRole().getName())));
    }
}
