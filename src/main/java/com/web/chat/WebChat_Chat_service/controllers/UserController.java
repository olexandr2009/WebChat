package com.web.chat.WebChat_Chat_service.controllers;

import com.web.chat.WebChat_Chat_service.config.jwt.JwtUtils;
import com.web.chat.WebChat_Chat_service.config.jwt.UserDetailsImpl;
import com.web.chat.WebChat_Chat_service.controllers.response.user.UpdateUserRequest;
import com.web.chat.WebChat_Chat_service.controllers.response.user.UpdateUserRoleRequest;
import com.web.chat.WebChat_Chat_service.controllers.response.user.UserResponse;
import com.web.chat.WebChat_Chat_service.exceptions.user.UserAlreadyExistException;
import com.web.chat.WebChat_Chat_service.exceptions.user.UserIncorrectPasswordException;
import com.web.chat.WebChat_Chat_service.exceptions.user.UserNotFoundException;
import com.web.chat.WebChat_Chat_service.services.UserService;
import com.web.chat.WebChat_Chat_service.services.mappers.UserMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping("/V1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public ResponseEntity<?> getMain() {
        return ResponseEntity.ok("ok");
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest)
            throws UserNotFoundException, UserAlreadyExistException, UserIncorrectPasswordException {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetailsImpl authentication = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        return ResponseEntity.ok(userMapper.toUserResponse(
                userService.updateUser(authentication.getId(), userMapper.toUpdateUserDto(updateUserRequest))));
    }

    @PutMapping("/update/roles")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<UserResponse> updateUserRole(@Valid @RequestBody UpdateUserRoleRequest updateUserRoleRequest)
            throws UserNotFoundException {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetailsImpl authentication = (UserDetailsImpl) context.getAuthentication().getPrincipal();
        return ResponseEntity.ok(userMapper.toUserResponse(
                userService.updateUserRoles(authentication.getId(), updateUserRoleRequest.getRoles())));
    }
}