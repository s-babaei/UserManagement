package com.example.userManagment.controller;


import com.example.userManagment.configuration.JwtService;
import com.example.userManagment.model.dto.GetTokenRequest;
import com.example.userManagment.model.dto.GetTokenResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid GetTokenRequest getTokenRequest) {
        logger.info("Request received for login user :{}", getTokenRequest.toString());
        GetTokenResponse getTokenResponse = new GetTokenResponse();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(getTokenRequest.getUsername(), getTokenRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                getTokenResponse.setToken(jwtService.generateToken(authentication, getTokenRequest.getUsername()));
                return new ResponseEntity<>(getTokenResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(getTokenResponse, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.info("Error in login user :{} e: {}" , getTokenRequest, e.getMessage());
            return new ResponseEntity<>(getTokenResponse, HttpStatus.UNAUTHORIZED);
        }
    }
}
