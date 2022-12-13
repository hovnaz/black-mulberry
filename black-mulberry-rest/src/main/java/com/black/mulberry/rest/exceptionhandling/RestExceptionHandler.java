package com.black.mulberry.rest.exceptionhandling;

import com.black.mulberry.core.exception.*;
import com.black.mulberry.data.transfer.response.RestErrorResponse;
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
            ProductNotFoundException.class, ProductCommentNotExistException.class, UserNotFoundException.class,
            CategoryParentNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request){
        RestErrorResponse restErrorResponse = RestErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = UserEmailConflictException.class)
    public ResponseEntity<Object> handleUserEmailConflict(Exception ex, WebRequest request){
        RestErrorResponse restErrorResponse = RestErrorResponse.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = AuthenticatedException.class)
    public ResponseEntity<Object> handleAuthenticatedException(Exception ex, WebRequest request){
        RestErrorResponse restErrorResponse = RestErrorResponse.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {CategoryProductIsNotEmptyException.class, CategoryParentIsNotDeletedException.class})
    public ResponseEntity<Object> handleNonDeleteableDataException(Exception ex, WebRequest request){
        RestErrorResponse restErrorResponse = RestErrorResponse.builder()
                .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorResponse, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }
}
