package com.web.chat.WebChat_Chat_service.services.mappers;

import com.web.chat.WebChat_Chat_service.controllers.response.message.MessageResponse;
import com.web.chat.WebChat_Chat_service.controllers.response.message.UpdateMessageRequest;
import com.web.chat.WebChat_Chat_service.entities.MessageEntity;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.MessageDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.UpdateMessageDto;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MessageMapper {
    public MessageDto toMessageDto(MessageEntity message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setContent(message.getContent());
        dto.setChatName(message.getChat().getName());
        dto.setOwnerName(message.getOwner().getUsername());
        dto.setLastUpdatedDateTime(message.getLastUpdatedDateTime());
        return dto;
    }

    public UpdateMessageDto toUpdateMessageDto(UpdateMessageRequest request) {
        UpdateMessageDto dto = new UpdateMessageDto();
        dto.setUpdaterName(request.getUpdaterName());
        dto.setNewContent(request.getNewContent());
        return dto;
    }

    public MessageResponse toMessageResponse(MessageDto dto) {
        MessageResponse response = new MessageResponse();
        response.setId(dto.getId());
        response.setContent(dto.getContent());
        response.setLastUpdatedDateTime(dto.getLastUpdatedDateTime());
        response.setOwnerName(dto.getOwnerName());
        return response;
    }

    public List<MessageDto> messageDtos(Collection<MessageEntity> entities) {
        return entities.stream().map(this::toMessageDto).toList();
    }

    public List<MessageResponse> toMessageResponses(Collection<MessageDto> messageDtos) {
        return messageDtos.stream().map(this::toMessageResponse).toList();
    }
}
