package com.store.book.service.impl;

import com.store.book.dto.AddToCartDto;
import com.store.book.dto.CartDto;
import com.store.book.dto.CartItemDto;
import com.store.book.modal.Book;
import com.store.book.modal.Cart;
import com.store.book.modal.Customer;
import com.store.book.repository.CartRepository;
import com.store.book.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void addToCart(AddToCartDto addToCartDto, Book book, Customer customer) {
        Cart cart = new Cart(book, addToCartDto.getQuantity(), customer);
        cartRepository.save(cart);
    }

    @Override
    public CartDto listCartItems(Customer customer) {
        List<Cart> cartList = cartRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        BigDecimal amount = new BigDecimal(BigInteger.ZERO,  2);
        for (CartItemDto cartItemDto : cartItems) {
           BigDecimal totalCost = (cartItemDto.getBook().getPrice().multiply(new BigDecimal(cartItemDto.getQuantity())));
           amount.add(totalCost);
        }
        return new CartDto(cartItems, amount);
    }

    @Override
    public void deleteUserCartItems(Customer user) {
        cartRepository.deleteByCustomer(user);
    }

    public  CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }

}
