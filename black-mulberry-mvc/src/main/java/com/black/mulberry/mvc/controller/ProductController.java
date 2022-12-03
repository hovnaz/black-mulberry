package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.CategoryProductService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.response.CategoryProductResponse;
import com.black.mulberry.data.transfer.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/product")
@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryProductService categoryProductService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/add")
    public String goToProductAddPage(ModelMap modelMap) {
        List<CategoryProductResponse> categories = categoryProductService.findAll();
        modelMap.addAttribute("category", categories);
        return "view/addProduct";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update")
    public String goToProductUpdatePage(ModelMap modelMap, @PageableDefault Pageable pageable) {
        List<CategoryProductResponse> categories = categoryProductService.findAll();
        modelMap.addAttribute("category", categories);
        List<ProductResponse> product = productService.findAll(pageable);
        modelMap.addAttribute("product", product);
        return "view/updateProduct";
    }

    @GetMapping("/remove")
    public String removeProduct(@RequestParam("id") int id, @AuthenticationPrincipal CurrentUser currentUser) {
        productService.deleteById(id, currentUser.getId());
        return "redirect:/v1/product/my";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my")
    public String showMyAddedProducts(@PageableDefault Pageable pageable,
                                      @AuthenticationPrincipal CurrentUser currentUser,
                                      ModelMap modelMap) {
        List<ProductResponse> products = productService.findAllByUserId(currentUser.getId(), pageable);
        modelMap.addAttribute("products", products);
        return "view/myAddedProducts";
    }

    @GetMapping(value = "/getProductPic/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String fileName) throws IOException {
        return productService.getImage(fileName);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest,
                             @RequestParam(value = "image") MultipartFile file,
                             @AuthenticationPrincipal CurrentUser currentUser) {
        String fileName = productService.saveImage(file);
        productRequest.setPicUrl(fileName);
        productService.save(productRequest, currentUser.getId());
        return "redirect:/v1/product/my";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String updateProduct(@ModelAttribute ProductRequest productRequest,
                                @AuthenticationPrincipal CurrentUser currentUser,
                                @RequestParam(value = "image") MultipartFile file,
                                @PathVariable long id) {
        String fileName = productService.saveImage(file);
        productRequest.setPicUrl(fileName);
        productService.update(id, currentUser.getId(), productRequest);
        return "redirect:/v1/product/my";
    }
}