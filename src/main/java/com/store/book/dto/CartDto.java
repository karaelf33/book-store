package com.store.book.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartDto {

    private List<CartItemDto> cartItems;
    private BigDecimal totalCost;

    public CartDto(List<CartItemDto> cartItemDtoList, BigDecimal totalCost) {
        this.cartItems = cartItemDtoList;
        this.totalCost = totalCost;
    }
    }
