package com.checkout.checkout_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemDataModel, Long> {
    Optional<CartItemDataModel> findByCartIdAndItemId(Long cartId, Long itemId);
}
