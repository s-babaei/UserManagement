package com.example.userManagment.model.dto;

import com.example.userManagment.model.UserStatus;
import jakarta.persistence.Enumerated;
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
public class UserUpdateRequest {

    @Size(min=5)
    @NotNull(message = "please provide username")
    private String username;

    @Enumerated
    @NotNull(message = "please provide userStatus")
    private UserStatus userStatus;

}
