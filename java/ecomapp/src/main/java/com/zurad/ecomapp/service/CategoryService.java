package com.zurad.ecomapp.service;

import com.zurad.ecomapp.dto.CategoryDto;
import com.zurad.ecomapp.entity.Category;
import com.zurad.ecomapp.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired private CategoryRepository categoryrepository;

  public Optional<Category> getCategoryById(int categoryId) {
    return categoryrepository.findById(categoryId);
  }

  public List<Category> listCategories() {
    return categoryrepository.findAll();
  }

  public Category createCategory(CategoryDto category) {
    return categoryrepository.save(
        new Category(category.categoryName, category.description, category.imageURL));
  }

  public Category findCategoryByName(String categoryName) {
    return categoryrepository.findByCategoryName(categoryName);
  }

  public Optional<Category> findCategoryById(Integer categoryId) {
    return categoryrepository.findById(categoryId);
  }

  public void updateCategory(Integer categoryId, CategoryDto newCategory) {
    Category category = categoryrepository.findById(categoryId).get();
    category.setCategoryName(newCategory.categoryName);
    category.setDescription(newCategory.description);
    category.setImageUrl(newCategory.imageURL);
    categoryrepository.save(category);
  }

  public void deleteCategory(Integer categoryId) {
    categoryrepository.deleteById(categoryId);
  }
}
