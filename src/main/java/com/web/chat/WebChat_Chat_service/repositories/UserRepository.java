package com.web.chat.WebChat_Chat_service.repositories;

import com.web.chat.WebChat_Chat_service.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("FROM UserEntity ue WHERE ue.username IN :names")
    List<UserEntity> findByUsernames(@Param("names") Collection<String> names);
}