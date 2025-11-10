package com.zurad.ecomapp.controller;

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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

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
    void getCategoryById_ShouldReturnProperCategory() {
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
    void createCategory_createsCategory() {
        var category = new CategoryDto("some name", "imgUrl", "some desc");

        var response = restTemplate.postForEntity(path + "/create", new HttpEntity<>(category, headers), Category.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCategoryName()).isEqualTo("some name");
    }

    @Test
    void createCategory_whenIdAlreadyExists_ReturnsConflictResponse() {
        var category = new CategoryDto(1, "some name", "imgUrl", "some desc");

        var response = restTemplate.postForEntity(path + "/create", new HttpEntity<>(category, headers), Category.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }
}
