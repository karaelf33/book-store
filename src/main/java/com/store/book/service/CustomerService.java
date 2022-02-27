package com.store.book.service;

import com.store.book.dto.GenericDTO;
import com.store.book.dto.UserDto;
import com.store.book.model.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    GenericDTO createCustomerByUser(UserDto userDto);

    Customer findCustomerByUserId(Integer userId);
}
