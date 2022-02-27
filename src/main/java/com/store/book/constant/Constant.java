package com.store.book.constant;

public final class Constant {
    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static final int FAIL_CODE = 500;
    public static final String LENGTH_OF_USERNAME = "UserName length should not be grater than 30 characters";
    public static final String LENGTH_OF_PASSWORD = "password length should not be between 6 and 20 characters";
    public static final String LENGTH_OF_FIRST_NAME = "First Name length should not be grater than 140 characters";
    public static final String LENGTH_OF_EMAIL = "email length should not be grater than 40 characters";
    public static final String USER_NOT_PERMITTED = "user is not permitted to perform this operation";
    public static final String AUTH_TOEKN_NOT_PRESENT = "authentication token not present";
    public static final String AUTH_TOEKN_NOT_VALID = "authentication token not valid";
    public static final String USER_CREATED = "user created successfully";
    public static final String ID_NOT_PRESENT = "primary key is required for updating";
    public static final String WRONG_PASSWORD = "please check the pass";

    public enum Role {
        ADMIN,
        CUSTOMER,
        MERCHANT

    }

    public enum OrderStatus {
        DELIVERED,
        ON_THE_WAY,
        DECLINED,
        SENT_BACK

    }


    private Constant() {
    }
}
