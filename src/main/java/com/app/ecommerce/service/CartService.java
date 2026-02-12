package com.app.ecommerce.service;

import com.app.ecommerce.dto.CartItemRequest;
import com.app.ecommerce.dto.ProductResponse;
import com.app.ecommerce.model.CartItem;
import com.app.ecommerce.model.Product;
import com.app.ecommerce.model.User;
import com.app.ecommerce.repositories.CartItemRepository;
import com.app.ecommerce.repositories.ProductRepository;
import com.app.ecommerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
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

            int newTotalQuantity = existingCartItem.getQuantity() + request.getQuantity();
            if (product.getStockQtd() < newTotalQuantity) {
                return false;
            }

            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(newTotalQuantity)));
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

    public boolean deleteItemFromCart(UUID userId, Long productId) {

        Optional<Product> productOption = productRepository.findById(productId);
        Optional<User> userOption = userRepository.findById(userId);

        if (productOption.isPresent() && userOption.isPresent()) {
            cartItemRepository.deleteByUserAndProduct(userOption.get(), productOption.get());
            return true;
        }
        return false;
    }
}
