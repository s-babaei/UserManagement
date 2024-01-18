package com.example.userManagment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")

public class WelComeController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);


    @GetMapping("/welcome")
    @PreAuthorize("hasAuthority('user') Or hasAuthority('manager')")
    public String greeting() {
        logger.info("Request received for get welcome message");
        return "welcome to  my project";
    }


}
