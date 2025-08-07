package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.ChatMessage;
import com.example.gestionecoles.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}