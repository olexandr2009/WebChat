package com.web.chat.WebChat_Chat_service.exceptions.chat;

public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(String chatName) {
        super("Chat with name not found %s".formatted(chatName));
    }
}
