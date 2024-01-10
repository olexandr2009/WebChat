package com.web.chat.WebChat_Chat_service.entities.dtos.user;

import com.web.chat.WebChat_Chat_service.entities.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private LocalDateTime lastActiveTime;
    private Set<RoleEntity.UserRole> roles = new HashSet<>();
}