package com.web.chat.WebChat_Chat_service.services.mappers;

import com.web.chat.WebChat_Chat_service.controllers.response.chat.ChatResponse;
import com.web.chat.WebChat_Chat_service.controllers.response.chat.UpdateChatRequest;
import com.web.chat.WebChat_Chat_service.controllers.response.chat.UpdateChatResponse;
import com.web.chat.WebChat_Chat_service.entities.ChatEntity;
import com.web.chat.WebChat_Chat_service.entities.dtos.chat.ChatDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.chat.UpdateChatDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatMapper {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    UserMapper userMapper;

    public ChatDto toChatDto(ChatEntity chat) {
        ChatDto dto = new ChatDto();
        dto.setId(chat.getId());
        dto.setName(chat.getName());
        Set<MessageDto> messages = chat.getMessages().stream()
                .map(message -> messageMapper.toMessageDto(message))
                .collect(Collectors.toSet());
        dto.setMessages(messages);
        dto.setUsers(chat.getUsers().stream().map(userEntity -> userMapper.toUserDto(userEntity)).toList());

        return dto;
    }

    public UpdateChatDto toUpdateChatDto(UpdateChatRequest request) {
        UpdateChatDto dto = new UpdateChatDto();
        dto.setNewName(request.getNewName());
        dto.setOldName(request.getOldName());
        return dto;
    }

    public ChatResponse toChatResponse(ChatDto dto) {
        ChatResponse response = new ChatResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setMessages(dto.getMessages().stream().map(messageDto -> messageMapper.toMessageResponse(messageDto)).collect(Collectors.toSet()));
        response.setUsers(dto.getUsers().stream().map(userDto -> userMapper.toUserResponse(userDto)).toList());
        return response;
    }

    public UpdateChatResponse toUpdateChatResponse(UpdateChatRequest request) {
        UpdateChatResponse response = new UpdateChatResponse();
        response.setOldName(request.getOldName());
        response.setNewName(request.getNewName());
        return response;
    }

    public List<ChatResponse> toChatResponses(List<ChatDto> dtos) {
        if (dtos == null) {
            return new ArrayList<>();
        }
        return dtos.stream().map(this::toChatResponse).toList();
    }

    public List<ChatDto> toChatDtos(Collection<ChatEntity> entities) {
        if (entities == null) {
            return new ArrayList<>();
        }
        return entities.stream().map(this::toChatDto).toList();
    }
}
