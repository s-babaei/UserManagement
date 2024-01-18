package com.example.userManagment.model.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ManagerRequest {

    @NotNull
    private long id;


    @NotBlank(message = "name is necessary")
    @Size(min = 3, max = 20)
    private String firstName;


    @NotBlank(message = "lastName is necessary")
    @Size(min = 3, max = 20)
    private String lastName;

    @Email
    private String email;


}
