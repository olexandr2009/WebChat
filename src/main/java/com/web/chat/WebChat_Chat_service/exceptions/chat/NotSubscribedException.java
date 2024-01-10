package com.web.chat.WebChat_Chat_service.exceptions.chat;

public class NotSubscribedException extends RuntimeException {

    public NotSubscribedException(String chatName, String username) {
        super("User %s not subscribed to chat %s".formatted(username ,chatName));
    }
}
