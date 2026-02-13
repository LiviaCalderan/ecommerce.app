package com.app.ecommerce.dto;

import com.app.ecommerce.model.Order;
import com.app.ecommerce.model.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemsDTO {

    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;

}
