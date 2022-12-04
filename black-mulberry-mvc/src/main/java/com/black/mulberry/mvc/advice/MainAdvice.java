package com.black.mulberry.mvc.advice;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.data.transfer.model.UserRole;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MainAdvice {

    @ModelAttribute
    public void currentUser(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("currentUser", currentUser.getUser());
            if (currentUser.getUser().getRole() == UserRole.ADMIN){
                modelMap.addAttribute("isAdmin", true);
            }
        }
    }
}
