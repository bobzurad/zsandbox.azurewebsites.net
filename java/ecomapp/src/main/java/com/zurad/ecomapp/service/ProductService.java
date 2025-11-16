package com.zurad.ecomapp.service;

import com.zurad.ecomapp.dto.ProductDto;
import com.zurad.ecomapp.entity.Category;
import com.zurad.ecomapp.entity.Product;
import com.zurad.ecomapp.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired private ProductRepository productRepository;

  public Product createProduct(ProductDto productDto, Category category) {
    Product product = getProductFromDto(productDto, category);
    return productRepository.save(product);
  }

  public List<Product> listProducts() {
    return productRepository.findAll();
  }

  public static Product getProductFromDto(ProductDto productDto, Category category) {
    return new Product(
        productDto.name, productDto.imageUrl, productDto.price, productDto.description, category);
  }
}
