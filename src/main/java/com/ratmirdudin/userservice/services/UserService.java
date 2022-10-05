package com.ratmirdudin.userservice.services;

import com.ratmirdudin.userservice.domains.enums.Status;
import com.ratmirdudin.userservice.domains.models.User;
import com.ratmirdudin.userservice.dtos.CreateUserDto;
import com.ratmirdudin.userservice.dtos.UpdateUserStatusResponseDto;
import com.ratmirdudin.userservice.dtos.UserDto;
import com.ratmirdudin.userservice.exceptions.ResourceNotFoundException;
import com.ratmirdudin.userservice.mappers.UserMapper;
import com.ratmirdudin.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto createUser(CreateUserDto createUserDto) {
        log.info("Creating user with name: {}", createUserDto.getName());
        User user = userMapper.toEntity(createUserDto);
        user.setStatus(Status.Offline);
        User save = userRepository.save(user);
        return userMapper.toDto(save);
    }

    public UserDto getUserById(Long userId) {
        log.info("Finding user with id: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found."));

        return userMapper.toDto(user);
    }

    public UpdateUserStatusResponseDto updateUserStatus(Long userId, String status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found."));
        String oldStatus = user.getStatus().toString();
        user.setStatus(Status.findCaseInsensitive(status));
        User save = userRepository.save(user);
        return UpdateUserStatusResponseDto
                .builder()
                .id(save.getId())
                .oldStatus(oldStatus)
                .newStatus(save.getStatus().toString())
                .build();
    }
}
