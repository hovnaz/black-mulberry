package com.black.mulberry.mvc.advice;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.data.transfer.model.UserRole;
import com.black.mulberry.core.service.ProductBasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class MainAdvice {

    private final ProductBasketService productBasketService;

    @ModelAttribute
    public void currentUser(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null) {
            modelMap.addAttribute("currentUser", currentUser.getUser());
            modelMap.addAttribute("basketItemCount", productBasketService.countAllByUserId(currentUser.getId()));
            modelMap.addAttribute("isAuthenticated", true);
            modelMap.addAttribute("currentUserId", currentUser.getId());
            if (currentUser.getUser().getRole() == UserRole.ADMIN){
                modelMap.addAttribute("isAdmin", true);
            }
        }
    }
}
