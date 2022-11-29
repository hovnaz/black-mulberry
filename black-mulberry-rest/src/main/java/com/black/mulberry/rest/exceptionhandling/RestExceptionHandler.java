package com.black.mulberry.rest.exceptionhandling;

import com.black.mulberry.core.exception.*;
import com.black.mulberry.data.transfer.response.RestErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CategoryNotFoundException.class, ProductRatingNotExistException.class,
            ProductNotExistException.class, ProductCommentNotExistException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request){
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorDto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = UserEmailConflict.class)
    public ResponseEntity<Object> handleUserEmailConflict(Exception ex, WebRequest request){
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorDto, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = AuthenticatedException.class)
    public ResponseEntity<Object> handleAuthenticatedException(Exception ex, WebRequest request){
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
