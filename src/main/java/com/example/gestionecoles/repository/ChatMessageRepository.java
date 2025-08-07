package com.example.gestionecoles.repository;

import com.example.gestionecoles.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {}
