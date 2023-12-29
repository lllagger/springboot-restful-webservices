package com.javaguide.springboot.mapper;

import com.javaguide.springboot.dto.UserDto;
import com.javaguide.springboot.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFistName(),
                userDto.getLastName(),
                userDto.getEmail()
        );

        return user;
    }
}
