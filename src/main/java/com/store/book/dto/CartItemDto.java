package com.store.book.dto;

import com.store.book.model.Book;
import com.store.book.model.Cart;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CartItemDto {

    private Integer id;
    private @NotNull Integer quantity;
    private @NotNull Book book;

    public CartItemDto() {
    }

    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setBook(cart.getBook());
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", bookName=" + book.getBookName() +
                '}';
    }

}
