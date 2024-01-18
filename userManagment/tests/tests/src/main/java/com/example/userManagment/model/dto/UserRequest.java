package com.example.userManagment.model.dto;

import com.example.userManagment.model.UserStatus;
import com.example.userManagment.model.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private Long id;

    @NotBlank(message ="name is necessary")
    @Size(min =3,max = 20)
    private String firstName;

    @NotBlank(message ="lastName is necessary")
    @Size(min =3,max = 20)
    private String lastName;

    private String email;

    @NotBlank(message ="password is necessary")
    @Size(min =3,max = 8)
    @NotNull
    private String password;

    @NotBlank(message ="userName is necessary")
    @Size(min =3,max = 20)
    private String username;

    @Enumerated
    private UserStatus userStatus;

    @NotBlank(message ="roles is necessary")
    private String roles;



}
