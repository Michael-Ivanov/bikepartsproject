package com.bikeparsing.bikepartsapp.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handleUserNotAuthenticated(UserNotAuthenticatedException e, Model model) {
        model.addAttribute("exception", e);
        return "/access-denied";
    }
}
