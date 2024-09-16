package com.checkout.checkout_service.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.checkout.checkout_service.TestData.createCartItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartItemTest {

    Item apple;
    CartItem cartItem;

    @BeforeEach()
    void setUp() {
        cartItem = createCartItem();
        apple = cartItem.getItem();
    }

    @Test
    void getTotalPriceWhenDiscount() {
        double result = cartItem.getTotalPrice();

        assertEquals(120, result);
    }

    @Test
    void getTotalPriceWhenNoDiscount() {
        apple.setIsOffer(Boolean.FALSE);
        double result = cartItem.getTotalPrice();

        assertEquals(150, result);
    }

    @Test
    void getTotalPriceWhenQuantityForOfferNotReached() {
        cartItem.setQuantity(1);
        double result = cartItem.getTotalPrice();

        assertEquals(30, result);
    }
}