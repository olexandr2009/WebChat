package com.web.chat.WebChat_Chat_service.controllers;

import com.web.chat.WebChat_Chat_service.controllers.response.chat.ChatResponse;
import com.web.chat.WebChat_Chat_service.controllers.response.chat.UpdateChatRequest;
import com.web.chat.WebChat_Chat_service.controllers.response.chat.UpdateChatResponse;
import com.web.chat.WebChat_Chat_service.controllers.response.message.MessageResponse;
import com.web.chat.WebChat_Chat_service.controllers.response.user.UserResponse;
import com.web.chat.WebChat_Chat_service.entities.dtos.chat.ChatDto;
import com.web.chat.WebChat_Chat_service.services.ChatService;
import com.web.chat.WebChat_Chat_service.services.mappers.ChatMapper;
import com.web.chat.WebChat_Chat_service.services.mappers.MessageMapper;
import com.web.chat.WebChat_Chat_service.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/V1/chats")
public class ChatController {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping
    public ResponseEntity<List<ChatResponse>> findChatsForUser(Principal principal) {
        List<ChatDto> chatDtos = chatService.userChats(principal.getName());
        return ResponseEntity.ok(chatMapper.toChatResponses(chatDtos));
    }

    @GetMapping("/{chatName}/history")
    public ResponseEntity<List<MessageResponse>> findHistoryForChat(@PathVariable String chatName, Principal principal) {
        return ResponseEntity.ok(messageMapper.toMessageResponses(
                chatService.findHistoryForChat(chatName, principal.getName())));
    }
    @PostMapping("/{chatName}/subscribe")
    public ResponseEntity<?> subscribe(@PathVariable String chatName, Principal principal) {
        chatService.subscribeToChat(chatName, principal.getName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{chatName}/post")
    public ResponseEntity<?> postChat(@PathVariable String chatName, Principal principal) {
        chatService.registerChat(chatName, principal.getName());
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update")
    public ResponseEntity<UpdateChatResponse> updateChat(@RequestBody UpdateChatRequest request,
                                                         Principal principal) {
        chatService.updateChat(chatMapper.toUpdateChatDto(request), principal.getName());

        return ResponseEntity.ok(chatMapper.toUpdateChatResponse(request));
    }
    @GetMapping("/{chatName}/users")
    public ResponseEntity<List<UserResponse>> findChatUsers(@PathVariable String chatName){
        return ResponseEntity.ok(
                userMapper.toUserResponses(chatService.findUsersFromChat(chatName)));
    }
    @GetMapping("/like/{partOfName}")
    public ResponseEntity<List<ChatResponse>> findByPartOfName(@PathVariable String partOfName){
        return ResponseEntity.ok(chatMapper.toChatResponses(chatService.findByNameLike(partOfName)));
    }
    @PostMapping("/{chatName}/add_users")
    public ResponseEntity<List<ChatResponse>> addUsersToChat(@PathVariable String chatName,
                                                             @RequestBody List<String>  userNames,
                                                             Principal principal){
        chatService.addUsersToChat(chatName, userNames, principal.getName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{chatName}/delete_users")
    public ResponseEntity<ChatResponse> deleteUsersFromChat(@PathVariable String chatName,
                                                            @RequestBody List<String>  userNames,
                                                            Principal principal){
        chatService.deleteUsersFromChat(chatName, userNames, principal.getName());
        return ResponseEntity.ok(chatMapper.toChatResponse(chatService.findByName(chatName)));
    }
    @DeleteMapping("/{chatName}/delete")
    public ResponseEntity<ChatResponse> deleteChat(@PathVariable String chatName,
                                                   Principal principal){
        chatService.deleteChat(chatName, principal.getName());
        return ResponseEntity.ok().build();
    }
}
