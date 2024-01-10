package com.web.chat.WebChat_Chat_service.entities.dtos.message;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDto {
    private Integer id;
    private String content;
    private String chatName;
    private String ownerName;
    private LocalDateTime lastUpdatedDateTime;
    private LocalDateTime createdDateTime;
}