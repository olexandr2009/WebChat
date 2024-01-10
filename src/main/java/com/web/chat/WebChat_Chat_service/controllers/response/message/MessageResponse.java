package com.web.chat.WebChat_Chat_service.controllers.response.message;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageResponse {
    private Integer id;
    private String content;
    private String ownerName;
    private LocalDateTime lastUpdatedDateTime;
}