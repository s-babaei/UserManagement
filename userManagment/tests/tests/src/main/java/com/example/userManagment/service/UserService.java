package com.example.userManagment.service;


import com.example.userManagment.model.dto.*;


public interface UserService {


   UserResponse saveUser(UserSaveRequest userSaveRequest);

    UserResponse deleteUserByUsername(UserDeletedRequest userDeletedRequest);

    UserResponse updateUserByStatus(UserUpdateRequest userUpdateRequest) throws Exception;




}
