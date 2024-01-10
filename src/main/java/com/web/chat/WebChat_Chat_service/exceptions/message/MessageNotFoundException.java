package com.web.chat.WebChat_Chat_service.exceptions.message;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Integer id) {
        super("Message with id not found %d".formatted(id));

    }
}
