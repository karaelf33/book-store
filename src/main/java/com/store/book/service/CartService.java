package com.store.book.service;

import com.store.book.dto.AddToCartDto;
import com.store.book.dto.CartDto;
import com.store.book.dto.CartItemDto;
import com.store.book.modal.Book;
import com.store.book.modal.Cart;
import com.store.book.modal.Customer;

public interface CartService {

    void addToCart(AddToCartDto addToCartDto, Book book, Customer customer);

    CartDto listCartItems(Customer customer);

    public void deleteUserCartItems(Customer customer);

      CartItemDto getDtoFromCart(Cart cart);
}
