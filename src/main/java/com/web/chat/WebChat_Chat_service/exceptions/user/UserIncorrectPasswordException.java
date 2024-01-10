package com.web.chat.WebChat_Chat_service.exceptions.user;

public class UserIncorrectPasswordException extends Throwable {

    private static final String USER_INCORRECT_PASSWORD_EXCEPTION_TEXT =
            "Incorrect password for user with username = %s.";

    public UserIncorrectPasswordException(String username) {
        super(String.format(USER_INCORRECT_PASSWORD_EXCEPTION_TEXT, username));
    }
}