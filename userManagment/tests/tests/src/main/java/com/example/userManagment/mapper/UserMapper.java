package com.example.userManagment.mapper;

import com.example.userManagment.model.dto.UserRequest;
import com.example.userManagment.model.entity.User;
import com.example.userManagment.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper extends EntityMapper<UserRequest, User>{
}
