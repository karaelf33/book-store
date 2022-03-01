package com.store.book.service.impl;

import com.store.book.constant.Constant;
import com.store.book.dto.GenericDTO;
import com.store.book.dto.UserDto;
import com.store.book.exception.RunTimeExceptionPlaceHolder;
import com.store.book.model.User;
import com.store.book.repository.UserRepository;
import com.store.book.service.CustomerService;
import com.store.book.service.UserService;
import com.store.book.utils.OperationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerService customerService;


    @Override
    public GenericDTO registerUser(UserDto userDTO) {
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
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
                .role(userDTO.getRole())
                .build();
        try {
            userRepository.save(user);
            if (Objects.equals(userDTO.getRole(), Constant.Role.CUSTOMER.name())) {
                customerService.createCustomerByUser(userDTO);
            }
            logger.info("Created successfully");
            return OperationUtils.returnMessageHandling(
                    userDTO,
                    Constant.SUCCESS_CODE,
                    true,
                    Constant.SUCCESS_MESSAGE
            );
        } catch (Exception e) {
            logger.error("Failed to create user{}", e.getMessage(), e);
            return OperationUtils.returnMessageHandling(
                    userDTO,
                    Constant.FAIL_CODE,
                    false,
                    e.getMessage()
            );
        }


    }
}
