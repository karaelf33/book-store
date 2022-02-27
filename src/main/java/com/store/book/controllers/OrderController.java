package com.store.book.controllers;

import com.store.book.exception.AuthenticationFailException;
import com.store.book.model.Customer;
import com.store.book.model.Order;
import com.store.book.model.User;
import com.store.book.service.CustomerService;
import com.store.book.service.OrderService;
import com.store.book.service.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<List<Order>> listCustomerOrders(@RequestParam("token") String token)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Customer customer = customerService.findCustomerByUserId(user.getId());
        List<Order> orderListByCustomer = orderService.listCustomerOrders(customer.getId());
        return new ResponseEntity<>(orderListByCustomer, HttpStatus.CREATED);
    }
}
