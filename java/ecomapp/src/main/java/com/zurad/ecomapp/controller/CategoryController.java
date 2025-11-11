package com.zurad.ecomapp.controller;

import com.zurad.ecomapp.dto.CategoryDto;
import com.zurad.ecomapp.entity.Category;
import com.zurad.ecomapp.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

  @Autowired private CategoryService categoryService;

  @GetMapping("/")
  public ResponseEntity<List<Category>> getCategories() {
    List<Category> body = categoryService.listCategories();

    return new ResponseEntity<>(body, HttpStatus.OK);
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
    var category = categoryService.getCategoryById(categoryId);

    return category
        .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
  }

  @PostMapping("/create")
  public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDto category) {
    if (Objects.nonNull(categoryService.findCategoryByName(category.categoryName))) {
      return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }
    var newCategory = categoryService.createCategory(category);

    return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
  }

  @PostMapping("/update/{categoryId}")
  public ResponseEntity<Category> updateCategory(
      @PathVariable("categoryId") Integer categoryId, @Valid @RequestBody CategoryDto category) {
    // Check to see if the category exists.
    if (categoryService.findCategoryById(categoryId).isPresent()) {
      // If the category exists then update it.
      categoryService.updateCategory(categoryId, category);

      return new ResponseEntity<>(categoryService.getCategoryById(categoryId).get(), HttpStatus.OK);
    }

    // If the category doesn't exist then return a response of unsuccessful.
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  }

  @GetMapping("/delete/{categoryId}")
  public ResponseEntity<Object> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
    // Check to see if the category exists.
    if (categoryService.findCategoryById(categoryId).isPresent()) {
      // If the category exists then delete it.
      categoryService.deleteCategory(categoryId);
      return ResponseEntity.ok().build();
    }

    // If the category doesn't exist then return a response of unsuccessful.
    return ResponseEntity.notFound().build();
  }
}
