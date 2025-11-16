package com.zurad.ecomapp;

import static org.assertj.core.api.Assertions.assertThat;

import com.zurad.ecomapp.controller.CategoryController;
import com.zurad.ecomapp.controller.ProductController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// UseMainMethod ensures that SpringApplication.run(...) is covered by tests
@SpringBootTest(useMainMethod = SpringBootTest.UseMainMethod.ALWAYS)
class EcomappApplicationTests {

  @Autowired private CategoryController categoryController;

  @Autowired private ProductController productController;

  @Test
  void contextLoads() {
    assertThat(categoryController).isNotNull();
    assertThat(productController).isNotNull();
  }
}
