package com.web.chat.WebChat_Chat_service.entities.dtos.chat;

import com.web.chat.WebChat_Chat_service.entities.dtos.message.MessageDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.user.UserDto;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatDto {
    private Integer id;
    private String name;
    private Set<MessageDto> messages;
    private List<UserDto> users;
}
