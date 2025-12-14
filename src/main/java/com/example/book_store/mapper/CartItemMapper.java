package com.example.book_store.mapper;

import com.example.book_store.dto.CartItemDto;
import com.example.book_store.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemDto toDto(CartItem cartItem);

    List<CartItemDto> toDtoList(List<CartItem> entities);

    CartItem toEntity(CartItemDto dto);

    default String mapStockLevel(CartItem cartItem){
        return cartItem.getBook().getStockLevel();
    }
}
