package com.example.gestionecoles.controller;

import com.example.gestionecoles.entity.ChatMessage;
import com.example.gestionecoles.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ChatMessageController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return chatMessageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMessage> getMessageById(@PathVariable Integer id) {
        return chatMessageRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ChatMessage createMessage(@RequestBody ChatMessage message) {
        return chatMessageRepository.save(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatMessage> updateMessage(@PathVariable Integer id, @RequestBody ChatMessage message) {
        return chatMessageRepository.findById(id).map(existing -> {
            message.setId(id);
            return ResponseEntity.ok(chatMessageRepository.save(message));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        chatMessageRepository.deleteById(id);
    }
}
