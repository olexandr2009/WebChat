package com.web.chat.WebChat_Chat_service.services;

import com.web.chat.WebChat_Chat_service.entities.dtos.message.MessageDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.UpdateMessageDto;
import jakarta.transaction.Transactional;

public interface MessageService {
    @Transactional
    Integer saveMessage(MessageDto messageDto);

    @Transactional
    MessageDto updateMessage(Integer id, UpdateMessageDto updateDto);
}
