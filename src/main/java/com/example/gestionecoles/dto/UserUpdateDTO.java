package com.example.gestionecoles.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateDTO {
    @NotBlank(message = "Name is required")
    private String name;
    
    @Pattern(regexp = "^\\+?[0-9\\s-()]+$", message = "Invalid phone number format")
    private String phone;
    
    private String favorites;
}