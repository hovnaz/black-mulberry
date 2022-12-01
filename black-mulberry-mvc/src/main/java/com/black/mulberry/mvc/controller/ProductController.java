package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.entity.CategoryProduct;
import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.repository.CategoryProductRepository;
import com.black.mulberry.core.repository.ProductRepository;
import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.mvc.util.MapUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;
    private final MapUtil mapUtil;
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

    @GetMapping("/{id}")
    public String viewProductDetail(ModelMap modelMap, @PathVariable long id,
                                    @PageableDefault(size = 25, sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable,
                                    @AuthenticationPrincipal CurrentUser currentUser) {
        Map<String, Object> map = mapUtil.productDetail(id, currentUser, pageable);
        modelMap.addAttribute("data", map);
        return "view/product-details";
    }
}
