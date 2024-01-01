package com.javaguide.springboot.controller;

import com.javaguide.springboot.dto.UserDto;
import com.javaguide.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        UserDto savedUserDto = userService.createUser(user);

        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User REST API is used to get a single user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUserById(userId);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Get All User By ID REST API",
            description = "Get All User REST API is used to get all the users from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userList = userService.getAllUsers();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @Operation(
            summary = "Update User REST API",
            description = "Update User REST API is used to update particular user in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto userDto) {
        userDto.setId(userId);
        UserDto updatedUserDto = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User REST API",
            description = "Delete User REST API is used to delete particular user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("Kullanıcı başarılı şekilde silindi", HttpStatus.OK);
    }

}
