package com.store.book.repository;

import com.store.book.modal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByUserId(Integer userId);
}
