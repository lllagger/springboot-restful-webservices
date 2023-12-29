package com.javaguide.springboot.service;

import com.javaguide.springboot.dto.UserDto;
import com.javaguide.springboot.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(User user);
    void deleteUser(Long userId);
}
