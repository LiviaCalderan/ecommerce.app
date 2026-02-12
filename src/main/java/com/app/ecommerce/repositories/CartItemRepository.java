package com.app.ecommerce.repositories;

import com.app.ecommerce.model.CarItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CarItem, Long> {
}
