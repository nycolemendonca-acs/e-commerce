package com.santana.java.back.end.exception.advice;

import com.santana.java.back.end.dto.ErrorDTO;
import com.santana.java.back.end.exception.UserNotFoundException;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.santana.java.back.end.controller")
public class UserControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)

    public ErrorDTO handleUserNotFound(
            UserNotFoundException userNotFoundException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Usuário não encontrado.");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }
}
