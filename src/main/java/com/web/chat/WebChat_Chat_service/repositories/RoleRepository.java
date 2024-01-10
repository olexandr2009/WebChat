package com.web.chat.WebChat_Chat_service.repositories;

import com.web.chat.WebChat_Chat_service.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEntity.UserRole name);

    @Query("FROM RoleEntity re WHERE re.name IN :names")
    Set<RoleEntity> findByNames(@Param("names") Collection<RoleEntity.UserRole> names);
}