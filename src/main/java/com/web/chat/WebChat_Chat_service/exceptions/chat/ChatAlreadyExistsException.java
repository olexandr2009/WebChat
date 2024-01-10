package com.web.chat.WebChat_Chat_service.exceptions.chat;

public class ChatAlreadyExistsException extends RuntimeException {
    public ChatAlreadyExistsException(String name) {
        super("Chat with name %s already exists".formatted(name));
    }
}
