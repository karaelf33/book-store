package com.store.book.dto;

import com.store.book.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.store.book.constant.Constant.*;
import static com.store.book.constant.Constant.LENGTH_OF_USERNAME;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDto {
    @NotBlank
    @Size(max = 40, message = LENGTH_OF_USERNAME)
    private String userName;

    @NotBlank
    @Size(max = 140, message = Constant.LENGTH_OF_ADDRESS)
    @Email
    private String address;

    @Size(max = 16, message = Constant.LENGTH_OF_CREDIT_CARD)
    private String creditCard;
}
