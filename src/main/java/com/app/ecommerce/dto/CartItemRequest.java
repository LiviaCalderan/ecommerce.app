package com.app.ecommerce.dto;

import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemRequest {

    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
