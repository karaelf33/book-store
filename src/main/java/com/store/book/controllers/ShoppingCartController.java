package com.store.book.controllers;


import com.store.book.dto.AddToCartDto;
import com.store.book.exception.AuthenticationFailException;
import com.store.book.exception.BookNotExistException;
import com.store.book.model.Book;
import com.store.book.model.Customer;
import com.store.book.model.User;
import com.store.book.service.BookService;
import com.store.book.service.CartService;
import com.store.book.service.CustomerService;
import com.store.book.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class ShoppingCartController {

    @Autowired
    private CartService cartService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    BookService bookService;
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDto addToCartDto,
                                            @RequestParam("token") String token)
            throws AuthenticationFailException,
            BookNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Customer customer=customerService.findCustomerByUserId(user.getId());
        Book book = bookService.getBookById(addToCartDto.getBookId());
        cartService.addToCart(addToCartDto, book, customer);
        return new ResponseEntity<>(("Added to cart"), HttpStatus.CREATED);

    }

}
