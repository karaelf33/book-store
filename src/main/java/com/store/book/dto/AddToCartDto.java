package com.store.book.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddToCartDto {

    private Integer id;
    private @NotNull Integer bookId;
    private @NotNull Integer quantity;

    public AddToCartDto() {
    }


    @Override
    public String toString() {
        return "AddToCartDto{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", quantity=" + quantity +
                '}';
    }
}
