package com.web.chat.WebChat_Chat_service.services.impl;

import com.web.chat.WebChat_Chat_service.entities.BaseEntity;
import com.web.chat.WebChat_Chat_service.entities.ChatEntity;
import com.web.chat.WebChat_Chat_service.entities.MessageEntity;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.MessageDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.UpdateMessageDto;
import com.web.chat.WebChat_Chat_service.exceptions.chat.ChatNotFoundException;
import com.web.chat.WebChat_Chat_service.exceptions.chat.NotSubscribedException;
import com.web.chat.WebChat_Chat_service.exceptions.message.MessageNotFoundException;
import com.web.chat.WebChat_Chat_service.repositories.ChatRepository;
import com.web.chat.WebChat_Chat_service.repositories.MessageRepository;
import com.web.chat.WebChat_Chat_service.repositories.UserRepository;
import com.web.chat.WebChat_Chat_service.services.MessageService;
import com.web.chat.WebChat_Chat_service.services.mappers.MessageMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageMapper mapper;

    @Override
    @Transactional
    public Integer saveMessage(MessageDto dto) {
        MessageEntity entity = new MessageEntity();
        entity.setId(dto.getId());
        entity.setContent(dto.getContent());
        entity.setChat(chatRepository.findByName(dto.getChatName())
                .orElseThrow(() -> new ChatNotFoundException(dto.getChatName())));
        entity.setOwner(userRepository.findByUsername(dto.getOwnerName())
                .orElseThrow(() -> new UsernameNotFoundException(dto.getOwnerName())));
        entity.setLastUpdatedDateTime(LocalDateTime.now());
        entity.setCreatedDateTime(LocalDateTime.now());

        updateChat(entity);
        messageRepository.save(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public MessageDto updateMessage(Integer id, UpdateMessageDto updateDto) {
        MessageEntity entity = messageRepository.findById(id)
                .orElseThrow(() -> new MessageNotFoundException(id));
        String updaterName = updateDto.getUpdaterName();
        if (updaterName == null){
            throw new RuntimeException();
        }
        if (!updaterName.equals(entity.getOwner().getUsername())){
            throw new RuntimeException();
        }
        entity.setContent(updateDto.getNewContent());
        entity.setLastUpdatedDateTime(LocalDateTime.now());

        updateChat(entity);
        messageRepository.save(entity);
        return mapper.toMessageDto(entity);
    }

    //    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Transactional
    private void updateChat(MessageEntity messageEntity) {
        ChatEntity chatEntity = messageEntity.getChat();
        chatEntity.setLastUpdatedDateTime(LocalDateTime.now());
        chatRepository.save(chatEntity);
    }
}
