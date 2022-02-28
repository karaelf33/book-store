package com.store.book.service.impl;

import com.store.book.constant.Constant;
import com.store.book.dto.GenericDTO;
import com.store.book.dto.UserDto;
import com.store.book.model.Customer;
import com.store.book.repository.CustomerRepository;
import com.store.book.service.CustomerService;
import com.store.book.utils.OperationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl  implements CustomerService {

    private static final Logger logger =  LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public GenericDTO createCustomerByUser(UserDto userDto) {

        Customer customer=Customer.builder()
                .userName(userDto.getFirstName())
                .build();
        try {

            customerRepository.save(customer);
            logger.info("Created successfully");
            return OperationUtils.returnMessageHandling(
                    null,
                    Constant.SUCCESS_CODE,
                    true,
                    Constant.SUCCESS_MESSAGE
            );
        }catch (Exception e){
            logger.error("Failed to create customer{}",e.getMessage(),e);
            return OperationUtils.returnMessageHandling(
                    null,
                    Constant.FAIL_CODE,
                    false,
                    e.getMessage()
            );
        }
    }

    @Override
    public Customer findCustomerByUserId(Integer userId) {
        return customerRepository.findByUserId(userId);
    }

}
