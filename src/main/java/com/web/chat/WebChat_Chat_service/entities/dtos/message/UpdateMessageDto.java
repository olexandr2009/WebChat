package com.web.chat.WebChat_Chat_service.entities.dtos.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMessageDto {
    private String newContent;
    private String updaterName;
}