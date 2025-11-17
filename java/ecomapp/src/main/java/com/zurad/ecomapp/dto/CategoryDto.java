package com.zurad.ecomapp.dto;

import jakarta.validation.constraints.NotNull;

public class CategoryDto {

  public Integer id;
  public @NotNull String categoryName;
  public @NotNull String imageUrl;
  public @NotNull String description;

  public CategoryDto() {}

  public CategoryDto(String categoryName, String imageUrl, String description) {
    this.categoryName = categoryName;
    this.imageUrl = imageUrl;
    this.description = description;
  }

  public CategoryDto(Integer id, String categoryName, String imageUrl, String description) {
    this.id = id;
    this.categoryName = categoryName;
    this.imageUrl = imageUrl;
    this.description = description;
  }
}
