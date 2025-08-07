package com.example.gestionecoles.controller;

import com.example.gestionecoles.entity.ChatMessage;
import com.example.gestionecoles.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping
    public List<ChatMessage> getAllMessages() {
        return chatMessageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMessage> getMessageById(@PathVariable Integer id) {
        return chatMessageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ChatMessage createMessage(@RequestBody ChatMessage message) {
        return chatMessageService.save(message);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatMessage> updateMessage(@PathVariable Integer id, @RequestBody ChatMessage message) {
        return chatMessageService.findById(id).map(existing -> {
            message.setId(id);
            return ResponseEntity.ok(chatMessageService.save(message));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        chatMessageService.delete(id);
    }
}
