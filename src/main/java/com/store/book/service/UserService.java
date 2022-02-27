package com.store.book.service;

import com.store.book.dto.GenericDTO;
import com.store.book.dto.UserDto;

public interface UserService {

    GenericDTO registerUser(UserDto userDTO);
}
