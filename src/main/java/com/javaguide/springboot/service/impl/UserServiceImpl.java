package com.javaguide.springboot.service.impl;

import com.javaguide.springboot.dto.UserDto;
import com.javaguide.springboot.entity.User;
import com.javaguide.springboot.mapper.AutoUserMapper;
import com.javaguide.springboot.mapper.UserMapper;
import com.javaguide.springboot.repository.UserRepository;
import com.javaguide.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
//        User user = UserMapper.mapToUser(userDto);
//        User user = modelMapper.map(userDto, User.class);

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

//        UserDto savedUser = UserMapper.mapToUserDto(savedUser);
//        UserDto savedUser = modelMapper.map(user, UserDto.class);
        UserDto savedUser = AutoUserMapper.MAPPER.mapToUserDto(user);

        return savedUser;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

//        User user = optionalUser.get();
//        return UserMapper.mapToUserDto(user);
//        return modelMapper.map(user, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(optionalUser.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
//        return userList.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());

//        return userList.stream().map((user -> modelMapper.map(user, UserDto.class)))
//                .collect(Collectors.toList());

        return userList.stream().map((user) -> AutoUserMapper.MAPPER.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
