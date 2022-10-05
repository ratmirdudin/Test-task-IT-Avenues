package com.ratmirdudin.userservice.mappers;

import com.ratmirdudin.userservice.dtos.CreateUserDto;
import com.ratmirdudin.userservice.dtos.UserDto;
import com.ratmirdudin.userservice.domains.models.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public User toEntity(CreateUserDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, User.class);
    }

    public UserDto toDto(User entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, UserDto.class);
    }
}
