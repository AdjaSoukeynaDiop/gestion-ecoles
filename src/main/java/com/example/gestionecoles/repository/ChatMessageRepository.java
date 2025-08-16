package com.example.gestionecoles.repository;

import com.example.gestionecoles.entity.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    Page<ChatMessage> findByUserIdOrderByTimestampDesc(Integer userId, Pageable pageable);
    
    @Query("SELECT cm FROM ChatMessage cm WHERE " +
           "(cm.user.id = :user1Id OR cm.user.id = :user2Id) " +
           "ORDER BY cm.timestamp ASC")
    Page<ChatMessage> findConversationBetweenUsersOrderByTimestamp(
            @Param("user1Id") Integer user1Id, 
            @Param("user2Id") Integer user2Id, 
            Pageable pageable);
}
