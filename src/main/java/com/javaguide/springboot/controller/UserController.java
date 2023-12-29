package com.javaguide.springboot.controller;

import com.javaguide.springboot.dto.UserDto;
import com.javaguide.springboot.entity.User;
import com.javaguide.springboot.exception.ErrorDetails;
import com.javaguide.springboot.exception.ResourceNotFoundException;
import com.javaguide.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto savedUserDto = userService.createUser(user);

        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userList = userService.getAllUsers();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody User user){
        user.setId(userId);
        UserDto updatedUserDto = userService.updateUser(user);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("Kullanıcı başarılı şekilde silindi", HttpStatus.OK);
    }

}
