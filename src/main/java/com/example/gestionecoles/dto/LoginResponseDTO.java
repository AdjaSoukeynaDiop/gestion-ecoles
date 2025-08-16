package com.example.gestionecoles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private UserResponseDTO user;
}