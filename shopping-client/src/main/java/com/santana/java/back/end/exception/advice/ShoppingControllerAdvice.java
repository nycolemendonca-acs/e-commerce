package com.santana.java.back.end.exception.advice;

import com.santana.java.back.end.dto.ErrorDTO;
import com.santana.java.back.end.exception.ProductNotFoundException;
import com.santana.java.back.end.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.santana.java.back.end.controller")
public class ShoppingControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public static ErrorDTO handleUserNotFound(ProductNotFoundException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Product not found.");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)

    public static ErrorDTO handleUserNotFound(UserNotFoundException userNotFoundException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("User not found.");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }
}
