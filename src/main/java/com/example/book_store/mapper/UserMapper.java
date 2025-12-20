package com.example.book_store.mapper;

import com.example.book_store.dto.UserCreateDto;
import com.example.book_store.dto.UserResponseDto;
import com.example.book_store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "username", source = "username")
    User toEntity(UserCreateDto dto);

    UserResponseDto toDto(User user);
}