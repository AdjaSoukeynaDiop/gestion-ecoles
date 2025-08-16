package com.example.gestionecoles.controller;

import com.example.gestionecoles.dto.LoginResponseDTO;
import com.example.gestionecoles.dto.UserResponseDTO;
import com.example.gestionecoles.enums.Role;
import com.example.gestionecoles.entity.User;
import com.example.gestionecoles.exception.EmailAlreadyExistsException;
import com.example.gestionecoles.exception.InvalidCredentialsException;
import com.example.gestionecoles.repository.UserRepository;
import com.example.gestionecoles.utils.DTOMapper;
import com.example.gestionecoles.utils.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final DTOMapper dtoMapper;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, 
                         JwtUtil jwtUtil, DTOMapper dtoMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.dtoMapper = dtoMapper;
    }

    public static class SignupRequest {
        @NotBlank(message = "Name is required")
        public String name;
        
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        public String email;
        
        public String phone;
        
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        public String password;
    }

    public static class LoginRequest {
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        public String email;
        
        @NotBlank(message = "Password is required")
        public String password;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody SignupRequest request) {
        if (userRepository.findByEmail(request.email).isPresent()) {
            throw new EmailAlreadyExistsException(request.email);
        }

        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPhone(request.phone);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(Role.USER);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(dtoMapper.toUserResponseDTO(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new InvalidCredentialsException());

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtUtil.generateToken(request.email);
        UserResponseDTO userDTO = dtoMapper.toUserResponseDTO(user);
        return ResponseEntity.ok(new LoginResponseDTO(token, userDTO));
    }
}
