package com.app.ecommerce.controller;

import com.app.ecommerce.dto.CartItemRequest;
import com.app.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader ("X-User-ID") UUID userId,
                                          @RequestBody CartItemRequest request) {
        if(!cartService.addToCart(userId, request)) {
            return ResponseEntity.badRequest().body("Product Out of Stocke or User Not found ou Product Not Found");
        };
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
