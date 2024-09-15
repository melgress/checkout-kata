package com.checkout.checkout_service.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cart {

    Long id;

    private List<CartItem> cartItems = new ArrayList<>();

    public double calculateTotalPrice() {
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

}
