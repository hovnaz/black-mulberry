package com.black.mulberry.mvc.advice;

import com.black.mulberry.core.exception.AuthenticatedException;
import com.black.mulberry.core.exception.CategoryNotFoundException;
import com.black.mulberry.core.exception.CategoryParentIsNotDeletedException;
import com.black.mulberry.core.exception.CategoryParentNotFoundException;
import com.black.mulberry.core.exception.CategoryProductIsNotEmptyException;
import com.black.mulberry.core.exception.ProductCommentNotExistException;
import com.black.mulberry.core.exception.ProductNotExistException;
import com.black.mulberry.core.exception.ProductRatingNotExistException;
import com.black.mulberry.core.exception.UserEmailConflictException;
import com.black.mulberry.core.exception.UserNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CategoryNotFoundException.class,
            CategoryParentNotFoundException.class,
            ProductCommentNotExistException.class,
            ProductNotExistException.class,
            ProductRatingNotExistException.class,
            UserNotFoundException.class
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
            CategoryProductIsNotEmptyException.class})
    public String handleIsNotDeleted(Model theModel){
        theModel.addAttribute("error", true);
        return "redirect:/";
    }
}

