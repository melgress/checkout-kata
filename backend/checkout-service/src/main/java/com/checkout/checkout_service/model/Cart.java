package com.checkout.checkout_service.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Getter
@Setter
public class Cart {

    Long id;

    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(CartItem cartItem) {
        Optional<CartItem> existingItem = cartItems.stream()
                .filter(item -> item.getId().equals(cartItem.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().incrementQuantity(cartItem.getQuantity());
        } else {
            cartItems.add(cartItem);
        }
    }

}
