package com.example.userManagment.model.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@Component
@ToString
public class UserResponse {

    private Integer resultCode;
    private String resultMsg;
    private Object data;


}
