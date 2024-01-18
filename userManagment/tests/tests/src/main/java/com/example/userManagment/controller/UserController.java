package com.example.userManagment.controller;


import com.example.userManagment.model.dto.UserRequest;
import com.example.userManagment.repository.UserRepository;
import com.example.userManagment.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
        logger.info("Request received for creating user :{}", userRequest.toString());

        var userResponse = userService.saveUser(userRequest);

        if (userResponse.getResultCode() == 0) {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/userName")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<?> updateUser(@Validated @RequestBody UserRequest userRequest) throws Exception {
        logger.info("Request received for updating userStatus: {}", userRequest.toString());
        var userResponse = userService.updateUserByStatus(userRequest);

        if (userResponse.getResultCode() == 0) {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/userName")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<?> deleteUserByUserName(@Valid @RequestBody UserRequest userRequest) throws Exception {
        logger.info("Request received for deleting user: {}", userRequest.toString());
        var userResponse = userService.deleteUserByUsername(userRequest);

        if (userResponse.getResultCode() == 0) {
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(userResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}