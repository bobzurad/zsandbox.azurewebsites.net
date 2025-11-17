package com.zurad.ecomapp.service;

import com.zurad.ecomapp.dto.ProductDto;
import com.zurad.ecomapp.entity.Category;
import com.zurad.ecomapp.entity.Product;
import com.zurad.ecomapp.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired private ProductRepository productRepository;

  public Product createProduct(ProductDto productDto, Category category) {
    return productRepository.save(
        new Product(
            productDto.name,
            productDto.imageUrl,
            productDto.price,
            productDto.description,
            category));
  }

  public Optional<Product> getProductById(Integer productId) {
    return productRepository.findById(productId);
  }

  public List<Product> listProducts() {
    return productRepository.findAll();
  }

  public List<Product> listProductsByCategoryId(Integer categoryId) {
    return productRepository.findByCategoryId(categoryId);
  }
}
