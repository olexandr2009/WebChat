package com.web.chat.WebChat_Chat_service.controllers.response.chat;

import com.web.chat.WebChat_Chat_service.controllers.response.message.MessageResponse;
import com.web.chat.WebChat_Chat_service.controllers.response.user.UserResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ChatResponse {
    private Integer id;
    private String name;
    private Set<MessageResponse> messages;
    private List<UserResponse> users;
}
