package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.repository.CategoryProductRepository;
import com.black.mulberry.core.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    private final CategoryProductRepository categoryProductRepository;

//    @Value("${blackMulberry.mvc.images.product}")
    private String folderPath = "todo";

    @GetMapping("/product/add")
    public String goToProductAddPage(ModelMap modelMap) {
        List<CategoryProduct> categories = categoryProductRepository.findAll();
        modelMap.addAttribute("categories", categories);
        return "view/addProduct";
    }

    @GetMapping(value = "/product/getProductPic", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("fileName") String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(folderPath + File.separator + fileName);
        return IOUtils.toByteArray(inputStream);
    }

    @PostMapping("/product/add")
    public String addEmployee(@ModelAttribute
                              @RequestParam(value = "image") MultipartFile file,
                              ModelMap modelMap, Product product) throws IOException {
        if (!file.isEmpty() && file.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File newFile = new File(folderPath + File.separator + fileName);
            file.transferTo(newFile);
            product.setPicUrl(fileName);
        }

        productRepository.save(product);
        return "redirect:/orders";
    }
}
