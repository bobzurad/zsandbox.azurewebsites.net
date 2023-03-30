package com.zurad.ecomapp.controller;

import com.zurad.ecomapp.common.ApiResponse;
import com.zurad.ecomapp.dto.ProductDto;
import com.zurad.ecomapp.entity.Category;
import com.zurad.ecomapp.entity.Product;
import com.zurad.ecomapp.service.CategoryService;
import com.zurad.ecomapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> body = productService.listProducts();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDto.categoryId);

        if (!optionalCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }

        Category category = optionalCategory.get();
        productService.addProduct(productDto, category);

        return new ResponseEntity<>(new ApiResponse(true, "product added successfully"), HttpStatus.CREATED);
    }
}
