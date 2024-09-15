package com.checkout.checkout_service;

import com.checkout.checkout_service.model.CartItem;
import com.checkout.checkout_service.model.Item;

public class TestData {

    public static Item createItem() {
        return Item.builder()
                .id(22L)
                .name("Apple")
                .price(30.0)
                .isOffer(Boolean.TRUE)
                .offerPrice(45.0)
                .offerQuantity(2)
                .build();
    }

    public static CartItem createCartItem() {
       return CartItem.builder()
                .cartId(12L)
                .item(createItem())
                .quantity(5)
                .build();
    }
}
