package com.store.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.store.book.constant.Constant.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String userId;

    @NotBlank
    @Size(max = 40, message = LENGTH_OF_USERNAME)
    private String userName;

    @NotBlank
    @Size(min = 3, max = 30, message = LENGTH_OF_PASSWORD)
    private String password;

    @NotBlank
    @Size(max = 140, message = LENGTH_OF_FIRST_NAME)
    private String firstName;

    private String lastName;

    @NotBlank
    @Size(max = 40, message = "LENGTH_OF_EMAIL")
    @Email
    private String email;

    private String roleNames;
}
