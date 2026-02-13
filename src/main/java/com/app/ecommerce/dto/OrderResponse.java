package com.app.ecommerce.dto;

import com.app.ecommerce.model.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemsDTO> items;
    private LocalDateTime updatedAt;


}
