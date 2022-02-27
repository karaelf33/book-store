package com.store.book.repository;

import com.store.book.model.Cart;
import com.store.book.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

   // Boolean existsByCustomer(String customerId);

    List<Cart> deleteByCustomer(Customer customer);

    List<Cart> findAllByCustomerOrderByCreatedDateDesc(Customer user);

}
