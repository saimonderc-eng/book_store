package com.example.book_store.mapper;

import com.example.book_store.dto.UserCreateDto;
import com.example.book_store.dto.UserResponseDto;
import com.example.book_store.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",builder = @Builder(disableBuilder = true))
public interface UserMapper {

    User toEntity(UserCreateDto dto);
    UserResponseDto toDto(User user);
}