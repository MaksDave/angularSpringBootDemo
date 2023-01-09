package com.maksdave.angularspringbootdemo.service;

import com.maksdave.angularspringbootdemo.entity.User;
import com.maksdave.angularspringbootdemo.entity.enums.Role;
import com.maksdave.angularspringbootdemo.exceptions.UserExistsException;
import com.maksdave.angularspringbootdemo.payload.request.SignupRequest;
import com.maksdave.angularspringbootdemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class UserService {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    public User createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getFirstname());
        user.setLastName(signupRequest.getLastname());
        user.setUserName(signupRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(signupRequest.getPassword()));
        user.getRoles().add(Role.USER);
        
        try{
            LOGGER.info("Saving User {}", signupRequest.getEmail());
            return userRepository.save(user);
        }catch (Exception e) {
            LOGGER.error("Error during registration. {}", e.getMessage());
            throw new UserExistsException("The user " + user.getUsername() + " already exists. Please check credentials");
        }
    }
}
