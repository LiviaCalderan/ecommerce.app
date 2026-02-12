package com.app.ecommerce.dto;

import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {

    private Long id;
    private User user;
    private Product product;
    private Integer quantity;
    private BigDecimal price;
}
