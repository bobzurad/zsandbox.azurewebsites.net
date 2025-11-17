package com.zurad.ecomapp.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.zurad.ecomapp.dto.ProductDto;
import com.zurad.ecomapp.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTests {

  @Autowired private TestRestTemplate restTemplate;

  @LocalServerPort private int port;

  private String path;
  private HttpHeaders headers;

  @BeforeEach
  void setUp() {
    path = "http://localhost:" + port + "/product";
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
  }

  @Test
  void getProducts_ShouldGetAllProducts() {
    var response = restTemplate.getForEntity(path + "/", Product[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var products = response.getBody();
    assertThat(products).isNotNull();
    assertThat(products.length).isGreaterThan(0);
  }

  @Test
  void getProductById_ShouldReturnProductById() {
    var response = restTemplate.getForEntity(path + "/1", Product.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var product = response.getBody();
    assertThat(product).isNotNull();
    assertThat(product.getId().equals(1));
  }

  @Test
  void getProductById_WhenProductNotFound_ShouldReturnNotFoundResponse() {
    var response = restTemplate.getForEntity(path + "/666", Product.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void getProductsByCategoryId_ShouldGetProductsByCategoryId() {
    var response = restTemplate.getForEntity(path + "/category/1", Product[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var products = response.getBody();
    assertThat(products).isNotNull();
    assertThat(products.length).isGreaterThan(0);
    assertThat(products[0].getCategory()).isNotNull();
    assertThat(products[0].getCategory().getId()).isEqualTo(1);
  }

  @Test
  void createProduct_ShouldCreateProduct() {
    var product = new ProductDto("some name", "some desc", 129.95, "imageUrl", 1);

    var response =
        restTemplate.postForEntity(
            path + "/create", new HttpEntity<>(product, headers), Product.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getName()).isEqualTo("some name");
  }

  @Test
  void createProduct_WhenCategoryIsInvalid_ShouldReturnForbiddenResponse() {
    var product = new ProductDto("some name", "some desc", 129.95, "imageUrl", 666);

    var response =
        restTemplate.postForEntity(
            path + "/create", new HttpEntity<>(product, headers), Product.class);

    assertThat(response.getBody()).isNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
  }
}
