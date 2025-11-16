package com.zurad.ecomapp.dto;

import jakarta.validation.constraints.NotNull;

public class ProductDto {

  public Integer id;
  public @NotNull String name;
  public @NotNull String imageUrl;
  public @NotNull double price;
  public @NotNull String description;
  public @NotNull Integer categoryId;

  public ProductDto(
      String name, String description, double price, String imageUrl, Integer categoryId) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.imageUrl = imageUrl;
    this.categoryId = categoryId;
  }
}
