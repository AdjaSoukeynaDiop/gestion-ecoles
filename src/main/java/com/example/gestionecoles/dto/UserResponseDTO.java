package com.example.gestionecoles.dto;

import com.example.gestionecoles.enums.Role;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserResponseDTO {
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private String phone;
    private Timestamp createdAt;
    private String favorites;
}