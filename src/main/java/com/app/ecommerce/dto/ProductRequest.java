package com.app.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQtd;
    private String category;
    private String imageUrl;

}
