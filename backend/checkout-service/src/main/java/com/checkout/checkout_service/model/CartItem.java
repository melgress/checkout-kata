package com.checkout.checkout_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItem{

    private Long id;
    private long cartId;
    private Item item;
    private int quantity;

    public CartItem(long cartId, Item item, int quantity) {
        this.cartId = cartId;
        this.item = item;
        this.quantity = quantity;

    }

    public void incrementQuantity(int amount) {
        this.quantity += amount;
    }
}
