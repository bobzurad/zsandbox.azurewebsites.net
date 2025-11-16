package com.zurad.ecomapp.controller;

import com.zurad.ecomapp.dto.ProductDto;
import com.zurad.ecomapp.entity.Category;
import com.zurad.ecomapp.entity.Product;
import com.zurad.ecomapp.service.CategoryService;
import com.zurad.ecomapp.service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired private ProductService productService;

  @Autowired private CategoryService categoryService;

  @GetMapping("/")
  public ResponseEntity<List<Product>> getProducts() {
    List<Product> body = productService.listProducts();
    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @PostMapping("/create")
  public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto) {
    Optional<Category> optionalCategory = categoryService.findCategoryById(productDto.categoryId);

    if (!optionalCategory.isPresent()) {
      return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    var newProduct = productService.createProduct(productDto, optionalCategory.get());

    return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
  }
}
