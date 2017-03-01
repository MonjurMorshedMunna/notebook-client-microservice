package com.notebook.services;

import com.notebook.entities.User;
import com.notebook.entities.UserRole;
import com.notebook.repositories.UserRepository;
import com.notebook.repositories.UserRoleRepository;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by munna on 2/25/17.
 */
@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        System.out.println(token);
        User user = userRepository.findByEmail(token.getName());
        
        
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        String tokenCredentials = encoder.encodePassword(token.getCredentials().toString(), "sha");

        if(!user.getPassword().equals(tokenCredentials)){
            throw new BadCredentialsException("The credentials are invalid");
        }
        UserRole userRole = userRoleRepository.findByUserEmail(user.getEmail());
        List<String> roles = new ArrayList<>();


        return new UsernamePasswordAuthenticationToken(user,
                user.getPassword(),
                AuthorityUtils.createAuthorityList(userRole.getRole().getName()));
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
