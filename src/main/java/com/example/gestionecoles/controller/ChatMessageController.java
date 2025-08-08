package com.example.gestionecoles.controller;

import com.example.gestionecoles.entity.ChatMessage;
import com.example.gestionecoles.entity.User;
import com.example.gestionecoles.exception.ResourceNotFoundException;
import com.example.gestionecoles.service.ChatMessageService;
import com.example.gestionecoles.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/messages")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<ChatMessage> getAllMessages(Pageable pageable) {
        return chatMessageService.findAll(pageable);
    }
    
    @GetMapping("/my-messages")
    public Page<ChatMessage> getMyMessages(Authentication authentication, Pageable pageable) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return chatMessageService.findByUserId(user.getId(), pageable);
    }
    
    @GetMapping("/conversation/{userId}")
    public Page<ChatMessage> getConversationWithUser(@PathVariable Integer userId, 
                                                     Authentication authentication, 
                                                     Pageable pageable) {
        String email = authentication.getName();
        User currentUser = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        
        User otherUser = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        
        return chatMessageService.findConversationBetweenUsers(currentUser.getId(), userId, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMessage> getMessageById(@PathVariable Integer id) {
        ChatMessage message = chatMessageService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ChatMessage", "id", id));
        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<ChatMessage> createMessage(@Valid @RequestBody ChatMessage message, 
                                                     Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        
        message.setUser(user);
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        
        ChatMessage savedMessage = chatMessageService.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatMessage> updateMessage(@PathVariable Integer id, 
                                                     @Valid @RequestBody ChatMessage message,
                                                     Authentication authentication) {
        ChatMessage existing = chatMessageService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ChatMessage", "id", id));
        
        String email = authentication.getName();
        if (!existing.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You can only update your own messages");
        }
        
        existing.setContent(message.getContent());
        existing.setSuggestions(message.getSuggestions());
        existing.setTimestamp(new Timestamp(System.currentTimeMillis()));
        
        ChatMessage updatedMessage = chatMessageService.save(existing);
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Integer id, Authentication authentication) {
        ChatMessage message = chatMessageService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ChatMessage", "id", id));
        
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        
        if (!message.getUser().getEmail().equals(email) && !user.getRole().name().equals("ADMIN")) {
            throw new RuntimeException("You can only delete your own messages or be an admin");
        }
        
        chatMessageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
