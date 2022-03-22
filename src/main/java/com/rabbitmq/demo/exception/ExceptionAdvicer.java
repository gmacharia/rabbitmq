package com.rabbitmq.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvicer extends Throwable {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){
        ApiError error = new ApiError(400, exception.getMessage(),
                request.getServletPath());
        BindingResult bindingResult = exception.getBindingResult();
        Map<String, String> validationError = new HashMap<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            validationError.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.setValidationError(validationError);
        return error;
    }
}
