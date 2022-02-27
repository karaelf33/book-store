package com.store.book.repository;

import com.store.book.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {

    Boolean existsByUserName(String userName);
    Boolean existsByEmail(String email);
}
