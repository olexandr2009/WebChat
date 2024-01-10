package com.web.chat.WebChat_Chat_service.services.impl;

import com.web.chat.WebChat_Chat_service.entities.BaseEntity;
import com.web.chat.WebChat_Chat_service.entities.ChatEntity;
import com.web.chat.WebChat_Chat_service.entities.MessageEntity;
import com.web.chat.WebChat_Chat_service.entities.UserEntity;
import com.web.chat.WebChat_Chat_service.entities.dtos.chat.ChatDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.chat.UpdateChatDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.MessageDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.user.UserDto;
import com.web.chat.WebChat_Chat_service.exceptions.chat.ChatAlreadyExistsException;
import com.web.chat.WebChat_Chat_service.exceptions.chat.ChatNotFoundException;
import com.web.chat.WebChat_Chat_service.exceptions.chat.NotSubscribedException;
import com.web.chat.WebChat_Chat_service.repositories.ChatRepository;
import com.web.chat.WebChat_Chat_service.repositories.UserRepository;
import com.web.chat.WebChat_Chat_service.services.ChatService;
import com.web.chat.WebChat_Chat_service.services.mappers.ChatMapper;
import com.web.chat.WebChat_Chat_service.services.mappers.MessageMapper;
import com.web.chat.WebChat_Chat_service.services.mappers.UserMapper;
import io.jsonwebtoken.lang.Objects;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional
    public void registerChat(String name, String ownerUsername) {
        Optional<ChatEntity> optionalChatEntity = chatRepository.findByName(name);
        if (optionalChatEntity.isPresent()) {
            throw new ChatAlreadyExistsException(name);
        }
        UserEntity owner = userRepository.findByUsername(ownerUsername)
                .orElseThrow(() -> new UsernameNotFoundException(ownerUsername));

        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setName(name);
        chatEntity.setLastUpdatedDateTime(LocalDateTime.now());
        chatEntity.setCreatedDateTime(LocalDateTime.now());
        chatEntity.setOwner(owner);


        owner.addChat(chatEntity);

        chatRepository.save(chatEntity);
        userRepository.save(owner);
    }

    @Override
    @Transactional
    public void subscribeToChat(String chatName, String subscriberName) {
        ChatEntity chatEntity = chatRepository.findByName(chatName).orElseThrow();

        Set<UserEntity> users = chatEntity.getUsers();
        users.add(userRepository.findByUsername(subscriberName)
                .orElseThrow(() -> new UsernameNotFoundException(subscriberName)));
        chatEntity.setUsers(users);
        chatRepository.save(chatEntity);
    }

    @Override
    @Transactional
    public ChatDto updateChat(UpdateChatDto updateChatDto, String updaterName) {
        String newName = updateChatDto.getNewName();
        String oldName = updateChatDto.getOldName();
        if (!chatRepository.existsByName(oldName) ||
                chatRepository.existsByName(newName)){
            throw new RuntimeException();
        }
        ChatEntity chatEntity = chatRepository.findByName(oldName)
                .orElseThrow(() -> new ChatNotFoundException(oldName));
        throwIfUsersNotEqual(chatEntity.getOwner(), userRepository.findByUsername(updaterName)
                .orElseThrow(() -> new UsernameNotFoundException(updaterName)));

        chatEntity.setName(newName);
        chatEntity.setLastUpdatedDateTime(LocalDateTime.now());

        chatRepository.save(chatEntity);
        return chatMapper.toChatDto(chatEntity);
    }

    @Override
    public List<UserDto> findUsersFromChat(String chatName) {
        return userMapper.toUserDtos(
                chatRepository.findByName(chatName)
                .orElseThrow(() -> new ChatNotFoundException(chatName))
                .getUsers());
    }


    @Override
    @Transactional
    public void deleteUsersFromChat(String chatName, Collection<String> usernames, String deleterName) {
        List<UserEntity> users = userRepository.findByUsernames(usernames);
        ChatEntity chatEntity = chatRepository.findByName(chatName)
                .orElseThrow(() -> new ChatNotFoundException(chatName));
        throwIfUsersNotEqual(chatEntity.getOwner(),
                userRepository.findByUsername(deleterName)
                        .orElseThrow(() -> new UsernameNotFoundException(deleterName)));

        for (UserEntity user : users) {
            user.deleteChat(chatEntity);
        }
    }
    @Override
    public List<MessageDto> findHistoryForChat(String chatName, String username) {
        if (!subscribedToChat(chatName, username)){
            throw new NotSubscribedException(chatName, username);
        }
        ChatEntity chatEntity = chatRepository.findByName(chatName)
                .orElseThrow(() -> new ChatNotFoundException(chatName));
        List<MessageEntity> list = new ArrayList<>(chatEntity.getMessages());
        list.sort(Comparator.comparing(BaseEntity::getLastUpdatedDateTime));
        return messageMapper.messageDtos(list);
    }

    @Override
    public List<ChatDto> findByNameLike(String name) {
        return chatMapper.toChatDtos(chatRepository.findByNameContains(name));
    }

    @Override
    public List<ChatDto> userChats(String username) {
        return chatMapper.toChatDtos(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException(username))
                        .getChats());
    }

    @Override
    public ChatDto findByName(String chatName) {
        return chatMapper.toChatDto(chatRepository.findByName(chatName)
                .orElseThrow(() -> new ChatNotFoundException(chatName)));
    }

    @Override
    @Transactional
    public void addUsersToChat(String chatName, List<String> userNames, String updaterName) {
        ChatEntity chatEntity = chatRepository.findByName(chatName).orElseThrow();
        throwIfUsersNotEqual(chatEntity.getOwner(),
                userRepository.findByUsername(updaterName)
                        .orElseThrow(() -> new UsernameNotFoundException(updaterName)));

        Set<UserEntity> users = chatEntity.getUsers();
        users.addAll(userRepository.findByUsernames(userNames));
        chatEntity.setUsers(users);
        chatRepository.save(chatEntity);
    }

    @Override
    @Transactional
    public void deleteChat(String chatName, String deleterName) {
        ChatEntity chatEntity = chatRepository.findByName(chatName).orElseThrow();
        throwIfUsersNotEqual(chatEntity.getOwner(),
                userRepository.findByUsername(deleterName)
                        .orElseThrow(() -> new UsernameNotFoundException(deleterName)));
        chatRepository.delete(chatEntity);
    }

    @Override
    public boolean subscribedToChat(String chatName, String username) {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return userEntity.getChats().contains(
                chatRepository.findByName(chatName).orElseThrow(() -> new ChatNotFoundException(chatName)));
    }

    private void throwIfUsersNotEqual(UserEntity user1, UserEntity user2){
        if (!Objects.nullSafeEquals(user1,user2)){
            throw new RuntimeException();
        }
    }
}
