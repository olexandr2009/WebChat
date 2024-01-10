package com.web.chat.WebChat_Chat_service.entities.dtos.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChatDto {
    private String oldName;
    private String newName;
}
