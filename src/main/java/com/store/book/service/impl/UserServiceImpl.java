package com.store.book.service.impl;

import com.store.book.constant.Constant;
import com.store.book.dto.GenericDTO;
import com.store.book.dto.UserDTO;
import com.store.book.exception.RunTimeExceptionPlaceHolder;
import com.store.book.model.User;
import com.store.book.repository.UserRepository;
import com.store.book.service.CustomerService;
import com.store.book.service.UserService;
import com.store.book.utils.OperationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl  implements UserService {

    /*@Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;*/


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;



    @Override
    public GenericDTO createUser(UserDTO userDTO) {
       // String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        String encodedPassword = userDTO.getPassword();

        if (userRepository.existsByUserName(userDTO.getUserName())) {
            throw new RunTimeExceptionPlaceHolder("Username is already exist!!");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RunTimeExceptionPlaceHolder("Email address already in use!!");
        }

        User user = User.builder()
                .userName(userDTO.getUserName())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(encodedPassword)
                .build();
        User savedUser = userRepository.save(user);
        if (userDTO.getRoleNames().equals("CUSTOMER")) {
            customerService.createCustomer(userDTO.getUserName());
        }
        return OperationUtils.returnMessageHandling(
                userDTO,
                Constant.SUCCESS_CODE,
                true,
                Constant.SUCCESS_MESSAGE
        );
    }
}
