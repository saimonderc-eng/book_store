package com.example.book_store.mapper;

import com.example.book_store.dto.CartItemDto;
import com.example.book_store.entity.CartItem;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemDto toDto(CartItem cartItem);
}
