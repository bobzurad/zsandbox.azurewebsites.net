package com.zurad.ecomapp.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.zurad.ecomapp.dto.CategoryDto;
import com.zurad.ecomapp.entity.Category;
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
class CategoryControllerTests {

  @Autowired private TestRestTemplate restTemplate;

  @LocalServerPort private int port;

  private String path;
  private HttpHeaders headers;

  @BeforeEach
  void setUp() {
    path = "http://localhost:" + port + "/category";
    headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
  }

  @Test
  void getCategories_ShouldReturnAllCategories() {
    var response = restTemplate.getForEntity(path + "/", Category[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var categories = response.getBody();
    assertThat(categories).isNotNull();
    assertThat(categories.length).isGreaterThan(0);
  }

  @Test
  void getCategoryById_ShouldReturnCategoryById() {
    var response = restTemplate.getForEntity(path + "/1", Category.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var category = response.getBody();
    assertThat(category).isNotNull();
    assertThat(category.getId().equals(1));
  }

  @Test
  void getCategoryById_WhenCategoryNotFound_ShouldReturnNotFoundResponse() {
    var response = restTemplate.getForEntity(path + "/666", Category.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void createCategory_ShouldCreateCategoryAndCheckForDuplicates() {
    var category = new CategoryDto("some name", "imgUrl", "some desc");

    // expect success
    var response =
        restTemplate.postForEntity(
            path + "/create", new HttpEntity<>(category, headers), Category.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getCategoryName()).isEqualTo("some name");

    // expect conflict
    response =
        restTemplate.postForEntity(
            path + "/create", new HttpEntity<>(category, headers), Category.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    assertThat(response.getBody()).isNull();
  }

  @Test
  void updateCategory_ShouldUpdateCategory() {
    var expectedName = "updated name";
    var expectedDesc = "updated desc";
    var expectedUrl = "updated url";
    var category = new CategoryDto(1, expectedName, expectedUrl, expectedDesc);

    var response =
        restTemplate.postForEntity(
            path + "/update/1", new HttpEntity<>(category, headers), Category.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().getCategoryName()).isEqualTo(expectedName);
    assertThat(response.getBody().getDescription()).isEqualTo(expectedDesc);
    assertThat(response.getBody().getImageUrl()).isEqualTo(expectedUrl);
  }

  @Test
  void updateCategory_WhenCategoryIdDoesNotExist_ShouldReturnNotFound() {
    var response =
        restTemplate.postForEntity(
            path + "/update/666",
            new HttpEntity<>(new CategoryDto(666, "name", "url", "desc"), headers),
            Category.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(response.getBody()).isNull();
  }

  @Test
  void deleteCategory_ShouldDeleteCategoryAndReturnOk() {
    var response = restTemplate.getForEntity(path + "/delete/3", Object.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void deleteCategory_WhenCategoryIdDoesNotExist_ShouldReturnNotFound() {
    var response = restTemplate.getForEntity(path + "/delete/666", Object.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}
