package com.checkout.checkout_service.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "cart_item")
public class CartItemDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private CartDataModel cart;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemDataModel item;

    private int quantity;

}
