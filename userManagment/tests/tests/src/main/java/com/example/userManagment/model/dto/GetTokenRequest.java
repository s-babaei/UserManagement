package com.example.userManagment.model.dto;


import lombok.Data;
import lombok.ToString;


@Data
public class GetTokenRequest {

    private String username;
    private String password;

}
