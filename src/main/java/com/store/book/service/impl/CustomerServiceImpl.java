package com.store.book.service.impl;

import com.store.book.dto.GenericDTO;
import com.store.book.model.Customer;
import com.store.book.repository.CustomerRepository;
import com.store.book.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl  implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public GenericDTO createCustomer(String username) {

        Customer customer=Customer.builder()
                .userName(username)
                .build();
        return null;
    }

    @Override
    public Customer findCustomerByUserId(Integer userId) {
        return customerRepository.findByUserId(userId);
    }

}
