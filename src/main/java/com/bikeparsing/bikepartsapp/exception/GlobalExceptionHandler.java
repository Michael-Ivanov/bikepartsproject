package com.bikeparsing.bikepartsapp.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handleUserNotAuthenticated(UserNotAuthenticatedException e) {
        return "/access-denied";
    }
}
