package com.ratmirdudin.userservice.controllers;

import com.ratmirdudin.userservice.dtos.CreateUserDto;
import com.ratmirdudin.userservice.dtos.UpdateUserStatusRequestDto;
import com.ratmirdudin.userservice.dtos.UpdateUserStatusResponseDto;
import com.ratmirdudin.userservice.dtos.UserDto;
import com.ratmirdudin.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody CreateUserDto createUserDto) {
        UserDto userDto = userService.createUser(createUserDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDto.getId());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "userId") Long userId) {
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UpdateUserStatusResponseDto> updateUserStatus(@PathVariable(name = "userId") Long userId,
                                                                        @RequestBody UpdateUserStatusRequestDto dto) {
        UpdateUserStatusResponseDto responseDto = userService.updateUserStatus(userId, dto.getStatus());
        return ResponseEntity.ok(responseDto);
    }
}

