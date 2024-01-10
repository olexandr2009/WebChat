package com.web.chat.WebChat_Chat_service.controllers.response.user;

import com.web.chat.WebChat_Chat_service.entities.RoleEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UpdateUserRoleRequest {

    @NotEmpty
    Set<RoleEntity.UserRole> roles = new HashSet<>();
}