package com.inventorymanagement.inventorymanagement.exception;

import com.inventorymanagement.inventorymanagement.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException exception){
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode()).build(),HttpStatus.valueOf(exception.getStatus()));
    }
}
