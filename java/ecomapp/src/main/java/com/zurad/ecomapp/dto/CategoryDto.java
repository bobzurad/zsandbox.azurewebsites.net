package com.zurad.ecomapp.dto;

import jakarta.validation.constraints.NotNull;

public class CategoryDto {

    public Integer id;
    public @NotNull String categoryName;
    public @NotNull String imageURL;
    public @NotNull String description;

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
