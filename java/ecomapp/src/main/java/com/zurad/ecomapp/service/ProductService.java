package com.zurad.ecomapp.service;

import com.zurad.ecomapp.dto.ProductDto;
import com.zurad.ecomapp.entity.Category;
import com.zurad.ecomapp.entity.Product;
import com.zurad.ecomapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        return new Product(
                productDto.name,
                productDto.imageURL,
                productDto.price,
                productDto.description,
                category
        );
    }
}
