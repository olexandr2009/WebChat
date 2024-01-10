package com.web.chat.WebChat_Chat_service.entities.dtos.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {
    private String oldUsername;
    private String oldPassword;
    private String newUsername;
    private String newPassword;
}