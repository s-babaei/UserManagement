package com.example.userManagment.model.dto;

import com.example.userManagment.model.UserStatus;
import com.example.userManagment.model.entity.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Component
@ToString
public class UserResponse {

    private Integer resultCode;
    private String resultMsg;
    private Object data;


}
