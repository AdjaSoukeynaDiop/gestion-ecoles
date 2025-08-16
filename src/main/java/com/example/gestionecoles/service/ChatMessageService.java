package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.ChatMessage;
import com.example.gestionecoles.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository repo;
    public List<ChatMessage> findAll() { return repo.findAll(); }
    public Optional<ChatMessage> findById(Integer id) { return repo.findById(id); }
    public ChatMessage save(ChatMessage m) { return repo.save(m); }
    public void delete(Integer id) { repo.deleteById(id); }
    public Page<ChatMessage> findAll(Pageable pageable) { return repo.findAll(pageable); }
    public Page<ChatMessage> findByUserId(Integer userId, Pageable pageable) {
        return repo.findByUserIdOrderByTimestampDesc(userId, pageable);
    }
    public Page<ChatMessage> findConversationBetweenUsers(Integer user1Id, Integer user2Id, Pageable pageable) {
        return repo.findConversationBetweenUsersOrderByTimestamp(user1Id, user2Id, pageable);
    }
}