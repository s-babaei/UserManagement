package com.example.userManagment.model.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class GetTokenRequest {

    private String username;
    private String password;

}
