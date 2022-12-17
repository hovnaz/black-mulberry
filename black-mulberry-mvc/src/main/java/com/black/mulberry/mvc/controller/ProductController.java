package com.black.mulberry.mvc.controller;

import com.black.mulberry.core.entity.Product;
import com.black.mulberry.core.mapper.ProductMapper;
import com.black.mulberry.core.security.CurrentUser;
import com.black.mulberry.core.service.CategoryProductService;
import com.black.mulberry.core.service.ProductSearchService;
import com.black.mulberry.core.service.ProductService;
import com.black.mulberry.data.transfer.request.ProductFilterRequest;
import com.black.mulberry.data.transfer.request.ProductRequest;
import com.black.mulberry.data.transfer.request.ProductSearchRequest;
import com.black.mulberry.data.transfer.response.CategoryProductResponse;
import com.black.mulberry.data.transfer.response.ProductResponse;
import com.black.mulberry.mvc.util.MapUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/v1/product")
@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryProductService categoryProductService;
    private final MapUtil mapUtil;
    private final ProductMapper productMapper;
    private final ProductSearchService productSearchService;

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
    public String addProduct(@Valid @ModelAttribute ProductRequest productRequest,
                             @RequestParam(value = "image") MultipartFile file,
                             @AuthenticationPrincipal CurrentUser currentUser) {
        String fileName = productService.saveImage(file);
        productRequest.setPicUrl(fileName);
        productService.save(productRequest, currentUser.getId());
        return "redirect:/v1/product/my";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String updateProduct(@Valid @ModelAttribute ProductRequest productRequest,
                                @AuthenticationPrincipal CurrentUser currentUser,
                                @RequestParam(value = "image") MultipartFile file,
                                @PathVariable long id) {
        String fileName = productService.saveImage(file);
        productRequest.setPicUrl(fileName);
        productService.update(id, currentUser.getId(), productRequest);
        return "redirect:/v1/product/my";
    }

    @GetMapping("/{id}")
    public String viewProductDetail(ModelMap modelMap, @PathVariable long id,
                                    @PageableDefault(size = 25, sort = {"createAt"}, direction = Sort.Direction.DESC) Pageable pageable,
                                    @AuthenticationPrincipal CurrentUser currentUser) {
        Map<String, Object> map = mapUtil.productDetail(id, currentUser, pageable);
        modelMap.addAttribute("data", map);
        return "view/product-details";
    }

    @GetMapping("/filteredPage")
    public String goToFilterPage(ModelMap modelMap, @PageableDefault Pageable pageable){
        List<CategoryProductResponse> categories = categoryProductService.findAll();
        modelMap.addAttribute("categories", categories);
        List<ProductResponse> all = productService.findAll(pageable);
        modelMap.addAttribute("products", all);
        return "view/products-filter";
    }

    @GetMapping("/search")
    public String searchForProduct(@Valid @ModelAttribute ProductSearchRequest productSearchRequest, ModelMap modelMap){
        List<Product> search = productSearchService.searchForProduct(productSearchRequest);
        String title = productSearchRequest.getTitle();
        modelMap.addAttribute("searchedProduct", search);
        modelMap.addAttribute( "title",title);
        return "view/products-filter";
    }

    @GetMapping("/filter")
    public String filterByPrice(@Valid @ModelAttribute ProductFilterRequest productFilterRequest, ModelMap modelMap){
        List<Product> products = productSearchService.filterProductByPrice(productFilterRequest);
        long minPrice = productFilterRequest.getMinPrice();
        long maxPrice  = productFilterRequest.getMinPrice();
        modelMap.addAttribute("searchedProduct", products);
        modelMap.addAttribute( "minPrice", minPrice);
        modelMap.addAttribute( "maxPrice", maxPrice);
        return "view/products-filter";
    }
}
