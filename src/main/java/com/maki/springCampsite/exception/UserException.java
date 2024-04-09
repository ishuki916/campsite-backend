package com.maki.springCampsite.exception;

public class UserException extends RuntimeException {
    public static final String ERROR_MSG = "錯誤";

    public static final String ID_ERROR = "User ID 錯誤";


    public UserException() {
        super(ERROR_MSG);
    }

    public UserException(String message) {
        super(message);
    }
}
