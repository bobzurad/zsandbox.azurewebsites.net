package com.zurad.ecomapp.dto;

public class CategoryDto {

  public Integer id;
  public String categoryName;
  public String imageURL;
  public String description;

  public CategoryDto() {}

  public CategoryDto(String categoryName, String imageURL, String description) {
    this.categoryName = categoryName;
    this.imageURL = imageURL;
    this.description = description;
  }

  public CategoryDto(Integer id, String categoryName, String imageURL, String description) {
    this.id = id;
    this.categoryName = categoryName;
    this.imageURL = imageURL;
    this.description = description;
  }
}
