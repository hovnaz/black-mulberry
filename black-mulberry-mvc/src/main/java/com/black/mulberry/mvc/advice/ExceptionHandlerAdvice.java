package com.black.mulberry.mvc.advice;

import com.black.mulberry.core.exception.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

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
    public String handleEntityNotFoundException() {
        return "view/404";
    }

    @ExceptionHandler(value = UserEmailConflictException.class)
    public String handleUserEmailConflict(Model theModel) {
        theModel.addAttribute("emailConflict", true);
        return "view/auth";
    }

    @ExceptionHandler(value = {
            AuthenticatedException.class,
            CategoryParentIsNotDeletedException.class,
            CategoryProductIsNotEmptyException.class,
            RepeatUsersException.class,
            OrderCancelFailException.class,
            OrderCompletedFailException.class,
            OrderConfirmedFailException.class,
            OrderOpenFailException.class,
            PaymentOpenFailException.class,
            PaymentPaidFailException.class,
            OrderConflictException.class,
            PaymentConflictException.class,
            ProductBasketNotExistException.class
    })
    public String handleIsNotDeleted(Model theModel){
        theModel.addAttribute("error", true);
        return "redirect:/";
    }
}
