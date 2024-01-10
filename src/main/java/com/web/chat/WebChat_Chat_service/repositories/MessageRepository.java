package com.web.chat.WebChat_Chat_service.repositories;

import com.web.chat.WebChat_Chat_service.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {
    Set<MessageEntity> findByContent(String content);

    Set<MessageEntity> findByChatId(Integer chatId);
}
