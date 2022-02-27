package com.store.book.service.impl;

import com.store.book.constant.Constant;
import com.store.book.dto.AddToCartDto;
import com.store.book.dto.CartDto;
import com.store.book.dto.CartItemDto;
import com.store.book.dto.GenericDTO;
import com.store.book.model.Book;
import com.store.book.model.Cart;
import com.store.book.model.Customer;
import com.store.book.repository.CartRepository;
import com.store.book.service.CartService;
import com.store.book.utils.OperationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger =  LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private CartRepository cartRepository;

    @Override
    public GenericDTO addToCart(AddToCartDto addToCartDto, Book book, Customer customer) {
        Cart cart = new Cart(book, addToCartDto.getQuantity(), customer);
        try{
            cartRepository.save(cart);
            return OperationUtils.returnMessageHandling(
                    null,
                    Constant.SUCCESS_CODE,
                    true,
                    Constant.BOOK_ADDED_TO_BASKET
            );
        }catch (Exception e){
            logger.error("Failed book add to basket{}",e.getMessage(),e);
            return OperationUtils.returnMessageHandling(
                    null,
                    Constant.FAIL_CODE,
                    false,
                    e.getMessage()
            );
        }
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
