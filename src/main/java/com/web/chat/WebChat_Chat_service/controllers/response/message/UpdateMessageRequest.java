package com.web.chat.WebChat_Chat_service.controllers.response.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMessageRequest {
    @Positive
    private Integer id;
    @NotBlank
    private String newContent;
    private String updaterName;
}
