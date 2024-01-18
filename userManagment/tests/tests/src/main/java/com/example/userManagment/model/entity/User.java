package com.example.userManagment.model.entity;

import com.example.userManagment.model.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;



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
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "userStatus")
    private UserStatus userStatus;

    @Column(name = "roles")
    private String roles;

}
