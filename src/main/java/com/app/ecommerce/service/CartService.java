package com.app.ecommerce.service;

import com.app.ecommerce.dto.CartItemRequest;
import com.app.ecommerce.dto.ProductResponse;
import com.app.ecommerce.model.CartItem;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.repositories.CartItemRepository;
import com.app.ecommerce.repositories.ProductRepository;
import com.app.ecommerce.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    public boolean addToCart(UUID userId, CartItemRequest request) {

        Optional<Product> productOption = productRepository.findById(request.getProductId());
        if (productOption.isEmpty())
            return false;

        Product product = productOption.get();
        if(product.getStockQtd() < request.getQuantity())
            return false;

        Optional<User> userOption = userRepository.findById(userId);
        if (userOption.isEmpty())
            return false;

        User user = userOption.get();

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user, product);
        if (existingCartItem!= null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(existingCartItem.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }
}
