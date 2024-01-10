package com.web.chat.WebChat_Chat_service.controllers.response.chat;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateChatRequest {
    @NotBlank
    private String oldName;
    @NotBlank
    private String newName;
}
