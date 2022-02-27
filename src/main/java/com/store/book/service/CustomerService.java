package com.store.book.service;

import com.store.book.dto.GenericDTO;
import com.store.book.modal.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    GenericDTO createCustomer(String username);
    Customer findCustomerByUserId(Integer userId);
}
