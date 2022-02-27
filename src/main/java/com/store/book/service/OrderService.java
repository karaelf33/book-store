package com.store.book.service;

import com.store.book.model.Customer;
import com.store.book.model.Order;

import java.util.List;

public interface OrderService {

     void placeOrder(Customer customer,String sessionId);
      List<Order> listCustomerOrders(Integer customerId);
}
