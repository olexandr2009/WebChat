package com.web.chat.WebChat_Chat_service.services;

import com.web.chat.WebChat_Chat_service.entities.dtos.chat.ChatDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.chat.UpdateChatDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.MessageDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.user.UserDto;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.List;

public interface ChatService {
    @Transactional
    void registerChat(String name, String owner);
    @Transactional
    void subscribeToChat(String chatName, String subscriberName);

    @Transactional
    ChatDto updateChat(UpdateChatDto updateChatDto, String updaterName);

    @Transactional
    List<UserDto> findUsersFromChat(String chatName);

    @Transactional
    void deleteUsersFromChat(String chatName, Collection<String> userDtos, String deleterName);

    List<ChatDto> findByNameLike(String name);

    List<ChatDto> userChats(String username);

    ChatDto findByName(String chatName);

    @Transactional
    void addUsersToChat(String chatName, List<String> userNames, String updaterName);

    @Transactional
    void deleteChat(String chatName, String deleterName);

    boolean subscribedToChat(String chatName, String user);
    List<MessageDto> findHistoryForChat(String chatName, String username);
}
