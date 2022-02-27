package com.store.book.service;

import com.store.book.model.Customer;

public interface OrderService {

     void placeOrder(Customer customer,String sessionId);
}
