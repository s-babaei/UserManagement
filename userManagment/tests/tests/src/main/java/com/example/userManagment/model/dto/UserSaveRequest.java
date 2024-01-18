package com.example.userManagment.model.dto;

import com.example.userManagment.model.UserStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSaveRequest {
    private Long id;

    @NotNull(message = "please provide firstName")
    private String firstName;

    @NotNull(message = "please provide lastName")
    private String lastName;

    @Email
    @NotNull(message = "please provide email")
    private String email;

    @Size(min=5)
    @NotNull(message = "please provide password")
    private String password;

    @Size(min=5)
    @NotNull(message = "please provide username")
    private String username;

    @Enumerated
    @NotNull(message = "please provide userStatus")
    private UserStatus userStatus;

    @NotNull(message = "please provide roles")
    private String roles;


}
