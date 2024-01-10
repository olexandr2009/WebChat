package com.web.chat.WebChat_Chat_service.controllers;

import com.web.chat.WebChat_Chat_service.controllers.response.message.MessageResponse;
import com.web.chat.WebChat_Chat_service.controllers.response.message.UpdateMessageRequest;
import com.web.chat.WebChat_Chat_service.entities.dtos.message.MessageDto;
import com.web.chat.WebChat_Chat_service.services.MessageService;
import com.web.chat.WebChat_Chat_service.services.mappers.MessageMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/V1/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageMapper mapper;

    @PostMapping("/post")
    public ResponseEntity<?> postMessageToChat(@RequestBody @Valid MessageDto messageDto, Principal principal) {
        messageDto.setOwnerName(principal.getName());
        messageService.saveMessage(messageDto);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/update")
    public ResponseEntity<MessageResponse> updateMessage(@RequestBody @Valid UpdateMessageRequest request, Principal principal) {
        request.setUpdaterName(principal.getName());
        MessageDto messageDto = messageService.updateMessage(request.getId(), mapper.toUpdateMessageDto(request));
        return ResponseEntity.ok(mapper.toMessageResponse(messageDto));
    }
}
