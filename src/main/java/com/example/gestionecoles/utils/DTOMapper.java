package com.example.gestionecoles.utils;

import com.example.gestionecoles.dto.UserResponseDTO;
import com.example.gestionecoles.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DTOMapper {
    
    public UserResponseDTO toUserResponseDTO(User user) {
        if (user == null) return null;
        
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPhone(user.getPhone());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setFavorites(user.getFavorites());
        
        return dto;
    }
}