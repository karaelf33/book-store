package com.store.book.controllers;

import com.store.book.dto.GenericDTO;
import com.store.book.dto.UserDto;
import com.store.book.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("")
    public GenericDTO orderOfCustomer(UserDto customerRequestDto) {
        return customerService.createCustomerByUser(customerRequestDto);
    }

}
