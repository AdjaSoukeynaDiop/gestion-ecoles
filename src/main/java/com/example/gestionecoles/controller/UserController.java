package com.example.gestionecoles.controller;

import com.example.gestionecoles.dto.UserResponseDTO;
import com.example.gestionecoles.dto.UserUpdateDTO;
import com.example.gestionecoles.entity.User;
import com.example.gestionecoles.exception.ResourceNotFoundException;
import com.example.gestionecoles.service.UserService;
import com.example.gestionecoles.utils.DTOMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private DTOMapper dtoMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        return users.map(dtoMapper::toUserResponseDTO);
    }
    
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(user));
    }
    
    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateCurrentUser(
            @Valid @RequestBody UserUpdateDTO updateDTO,
            Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        
        user.setName(updateDTO.getName());
        user.setPhone(updateDTO.getPhone());
        user.setFavorites(updateDTO.getFavorites());
        
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(updatedUser));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(user));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(savedUser));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody User user) {
        User existing = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setId(id);
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}