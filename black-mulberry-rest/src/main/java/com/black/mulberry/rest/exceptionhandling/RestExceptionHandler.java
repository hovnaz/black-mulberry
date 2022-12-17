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

    @ExceptionHandler(value = {
            CategoryNotFoundException.class,
            ProductRatingNotExistException.class,
            ProductNotFoundException.class,
            ProductCommentNotExistException.class,
            UserNotFoundException.class,
            CategoryParentNotFoundException.class,
            FileNotExistException.class,
            OrderCancelNotFoundException.class,
            OrderNotFoundException.class,
            PaymentNotFoundException.class,
    })
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorDto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {
            OrderCancelFailException.class,
            OrderCompletedFailException.class,
            OrderConfirmedFailException.class,
            OrderOpenFailException.class,
            PaymentOpenFailException.class,
            PaymentPaidFailException.class,
    })
    public ResponseEntity<Object> handleFailedDependency(Exception ex, WebRequest request) {
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.FAILED_DEPENDENCY.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorDto, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {
            UserEmailConflictException.class,
            RepeatUsersException.class,
            OrderConflictException.class,
            PaymentConflictException.class,
            ProductBasketNotExistException.class
    })
    public ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.CONFLICT.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorDto, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = AuthenticatedException.class)
    public ResponseEntity<Object> handleAuthenticatedException(Exception ex, WebRequest request) {
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorDto, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {CategoryProductIsNotEmptyException.class, CategoryParentIsNotDeletedException.class})
    public ResponseEntity<Object> handleNonDeleteableDataException(Exception ex, WebRequest request) {
        RestErrorDto restErrorDto = RestErrorDto.builder()
                .statusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                .errorMessage(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, restErrorDto, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }
}
