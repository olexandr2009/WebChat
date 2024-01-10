package com.web.chat.WebChat_Chat_service.services.mappers;

import com.web.chat.WebChat_Chat_service.controllers.response.user.UpdateUserRequest;
import com.web.chat.WebChat_Chat_service.controllers.response.user.UserResponse;
import com.web.chat.WebChat_Chat_service.entities.RoleEntity;
import com.web.chat.WebChat_Chat_service.entities.UserEntity;
import com.web.chat.WebChat_Chat_service.entities.dtos.user.UpdateUserDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public UserDto toUserDto(UserEntity user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles().stream()
                .map(RoleEntity::getName).collect(Collectors.toSet()));
        dto.setLastActiveTime(user.getLastActiveTime());
        return dto;
    }

    public UpdateUserDto toUpdateUserDto(UpdateUserRequest request) {
        UpdateUserDto dto = new UpdateUserDto();
        dto.setOldUsername(request.getOldUsername());
        dto.setOldPassword(request.getOldPassword());
        dto.setNewUsername(request.getNewUsername());
        dto.setNewPassword(request.getNewPassword());
        return dto;
    }

    public UserResponse toUserResponse(UserDto dto) {
        UserResponse response = new UserResponse();
        response.setId(dto.getId());
        response.setUsername(dto.getUsername());
        response.setRoles(dto.getRoles());
        response.setLastActiveTime(dto.getLastActiveTime());
        return response;
    }

    public List<UserDto> toUserDtos(Collection<UserEntity> entities) {
        if (entities == null){
            return new ArrayList<>();
        }
        return entities.stream().map(this::toUserDto).toList();
    }

    public List<UserResponse> toUserResponses(List<UserDto> dtos) {
        if (dtos == null){
            return new ArrayList<>();
        }
        return dtos.stream().map(this::toUserResponse).toList();
    }
}
