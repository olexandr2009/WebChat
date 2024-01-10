package com.web.chat.WebChat_Chat_service.controllers.response.user;

import com.web.chat.WebChat_Chat_service.entities.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserResponse {
    private Integer id;
    private String username;
    private LocalDateTime lastActiveTime;
    private Set<RoleEntity.UserRole> roles = new HashSet<>();
}