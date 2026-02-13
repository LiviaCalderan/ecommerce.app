package com.app.ecommerce.controller;

import com.app.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;
}
