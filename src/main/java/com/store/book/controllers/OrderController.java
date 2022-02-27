package com.store.book.controllers;

import com.store.book.exception.AuthenticationFailException;
import com.store.book.model.Customer;
import com.store.book.model.User;
import com.store.book.service.CustomerService;
import com.store.book.service.OrderService;
import com.store.book.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    CustomerService customerService;


    @PostMapping("/add")
    public ResponseEntity<String> placeOrder(@RequestParam("token") String token, @RequestParam("sessionId") String sessionId)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Customer customer = customerService.findCustomerByUserId(user.getId());
        orderService.placeOrder(customer, sessionId);
        return new ResponseEntity<>(("Order has been placed"), HttpStatus.CREATED);
    }
}
