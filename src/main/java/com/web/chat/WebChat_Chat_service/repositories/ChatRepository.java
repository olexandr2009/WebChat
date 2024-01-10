package com.web.chat.WebChat_Chat_service.repositories;

import com.web.chat.WebChat_Chat_service.entities.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {
    Optional<ChatEntity> findByName(String name);

    Set<ChatEntity> findByNameContains(String partOfName);

    boolean existsByName(String name);
}
