package com.zurad.ecomapp.dto;

import javax.validation.constraints.NotNull;

public class ProductDto {

    public Integer id;
    public @NotNull String name;
    public @NotNull String imageURL;
    public @NotNull double price;
    public @NotNull String description;
    public @NotNull Integer categoryId;
}
