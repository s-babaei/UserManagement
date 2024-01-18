package com.example.userManagment.service;


import com.example.userManagment.model.UserStatus;
import com.example.userManagment.model.dto.UserRequest;
import com.example.userManagment.model.dto.UserResponse;
import com.example.userManagment.model.entity.User;
import com.example.userManagment.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserResponse userResponse;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository) {
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(userRequest, User.class);
        user.setPassword(encoder.encode(userRequest.getPassword()));
        try {
            user = userRepository.save(user);
            logger.info("User created successfully, username: {}", user.getUsername());
            userResponse.setResultCode(0);
            userResponse.setResultMsg("successful");
        } catch (Exception e) {
            logger.info("Error in creating successfully, username: {}", user.getUsername());
            userResponse.setResultCode(500);
            userResponse.setResultMsg("Internal server error");
        }

        return userResponse;
    }

    @Override
    public UserResponse deleteUserByUsername(UserRequest userRequest) {

        try {
            Optional<User> finedUser = userRepository.findByUsername(userRequest.getUsername());
            if (finedUser.isPresent()) {
                userRepository.delete(finedUser.get());
                logger.info("User deleted successfully, username: {}", finedUser.get().getUsername());
                userResponse.setResultCode(0);
                userResponse.setResultMsg("successful");
            } else {
                logger.info("User not found, username: {}", finedUser.get().getUsername());
                userResponse.setResultCode(404);
                userResponse.setResultMsg("notFound");
            }
        } catch (Exception e) {
            logger.error("Error while deleting user", e.getMessage());
            userResponse.setResultCode(500);
            userResponse.setResultMsg("Internal server error");
        }

        return userResponse;
    }

    @Override
    public UserResponse updateUserByStatus(UserRequest userRequest) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(userRequest, User.class);
        user.setPassword(encoder.encode(userRequest.getPassword()));
        try {
            Optional<User> findByUsername = userRepository.findByUsername(user.getUsername());
            if (findByUsername.isPresent()) {
                if (findByUsername.get().getUserStatus().equals(UserStatus.ACTIVE))
                    findByUsername.get().setUserStatus(UserStatus.NOTACTIVE);
                userRepository.save(user);
                logger.info("User status updated successfully, username: {}", user.getUsername());
                userResponse.setResultCode(0);
                userResponse.setResultMsg("successful");
            }
        } catch (Exception e) {
            logger.error("Error in updating user status, username: {}, e: {}", user.getUsername(), e.getMessage());
            userResponse.setResultCode(500);
            userResponse.setResultMsg("Internal server error");
        }
        return userResponse;
    }


}