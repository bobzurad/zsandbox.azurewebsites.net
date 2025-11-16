package com.zurad.ecomapp.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class CategoryTests {

  @Test
  void toString_ShouldPrintObjectAsString() {
    var category = new Category("some name", "some desc", "some imageUrl");
    var expected =
        "{\"id\":null, \"categoryName\":\"some name\", \"description\":\"some desc\","
            + " \"imageUrl\":\"some imageUrl\"}";
    assertThat(category.toString()).isEqualTo(expected);
  }
}
