package com.example.userManagment.service;


import com.example.userManagment.model.dto.UserRequest;
import com.example.userManagment.model.dto.UserResponse;


public interface UserService {


   UserResponse saveUser(UserRequest userRequest);

    UserResponse deleteUserByUsername(UserRequest userRequest);

    UserResponse updateUserByStatus(UserRequest userRequest) throws Exception;




}
