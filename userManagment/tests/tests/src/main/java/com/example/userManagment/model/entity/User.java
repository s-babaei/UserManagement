package com.example.userManagment.model.entity;

import com.example.userManagment.model.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Email;


@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Email
    private String email;

    private String password;

    @Column(name = "username", unique = true)
    private String username;

    @Column
    private UserStatus userStatus;

    private String roles;

}
