package com.javaguide.springboot.service.impl;

import com.javaguide.springboot.dto.UserDto;
import com.javaguide.springboot.entity.User;
import com.javaguide.springboot.exception.EmailAlreadyExistsException;
import com.javaguide.springboot.exception.ResourceNotFoundException;
import com.javaguide.springboot.mapper.AutoUserMapper;
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

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

//        UserDto savedUser = UserMapper.mapToUserDto(savedUser);
//        UserDto savedUser = modelMapper.map(user, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(user);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

//        User user = optionalUser.get();
//        return UserMapper.mapToUserDto(user);
//        return modelMapper.map(user, UserDto.class);

        return AutoUserMapper.MAPPER.mapToUserDto(user);
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
    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = userRepository.save(existingUser);

//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {

        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );

        userRepository.deleteById(userId);
    }
}
