package com.notebook.controllers;

import com.notebook.entities.Role;
import com.notebook.entities.User;
import com.notebook.entities.UserRole;
import com.notebook.repositories.RoleRepository;
import com.notebook.repositories.UserRepository;
import com.notebook.repositories.UserRoleRepository;
import com.notebook.services.CustomUserDetailsService;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

/**
 * Created by munna on 2/25/17.
 */
@Controller
public class MvcControllers {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;


    @GetMapping("/login")
    String showLoginPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    String login(@ModelAttribute User user){
        User mUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if(mUser!=null){

            userDetailsService.loadUserByUsername(user.getEmail());

            return "home";
        }else{
            return "login";
        }
    }

    @GetMapping("/register")
    String showRegister(Model model){
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    @Transactional
    String registerUser(@ModelAttribute User user){
    	
    	if(user.getPassword().equals(user.getRetypePassword())){
    	    ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
    	    user.setPassword(encoder.encodePassword(user.getPassword(), "sha"));
    		userRepository.save(user);
    		Role role= roleRepository.getOne(Long.parseLong("1"));
            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUser(user);
            userRoleRepository.save(userRole);
        	userDetailsService.loadUserByUsername(user.getEmail());
        	return "home";
    	}
    	else{
    		return "register";
    	}
    	
    }
}
