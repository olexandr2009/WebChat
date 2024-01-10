package com.web.chat.WebChat_Chat_service.services;

import com.web.chat.WebChat_Chat_service.entities.RoleEntity;
import com.web.chat.WebChat_Chat_service.entities.dtos.user.UpdateUserDto;
import com.web.chat.WebChat_Chat_service.entities.dtos.user.UserDto;
import com.web.chat.WebChat_Chat_service.exceptions.user.UserAlreadyExistException;
import com.web.chat.WebChat_Chat_service.exceptions.user.UserIncorrectPasswordException;
import com.web.chat.WebChat_Chat_service.exceptions.user.UserNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Collection;

public interface UserService {
    @Transactional
    void registerUser(String username, String password)
            throws UserAlreadyExistException;

    @Transactional
    UserDto updateUser(Integer userId, UpdateUserDto updateUserDto)
            throws UserNotFoundException, UserIncorrectPasswordException, UserAlreadyExistException;

    @Transactional
    UserDto updateUserRoles(Integer userId, Collection<RoleEntity.UserRole> roles)
            throws UserNotFoundException;

    @Transactional
    void updateActiveTime(String username);
}
