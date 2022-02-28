package com.store.book.controllers;

import com.store.book.dto.GenericDTO;
import com.store.book.dto.UserDto;
import com.store.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public GenericDTO registerUser(@RequestBody UserDto userDTO) {
        return userService.registerUser(userDTO);
    }
}
