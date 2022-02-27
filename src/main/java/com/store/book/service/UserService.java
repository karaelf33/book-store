package com.store.book.service;

import com.store.book.dto.GenericDTO;
import com.store.book.dto.UserDTO;

public interface UserService {

    GenericDTO createUser(UserDTO userDTO);
}
