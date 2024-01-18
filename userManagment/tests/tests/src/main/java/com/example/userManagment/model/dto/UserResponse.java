package com.example.userManagment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Integer resultCode;
    private String resultMsg;
    private Object data;


}
