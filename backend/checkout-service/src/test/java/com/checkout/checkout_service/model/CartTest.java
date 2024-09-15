package com.checkout.checkout_service.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CartTest {

    @Test
    void calculateTotalPrice() {

        CartItem cartItem = mock(CartItem.class);
        CartItem cartItem2 = mock(CartItem.class);
        when(cartItem.getTotalPrice()).thenReturn(145.0);
        when(cartItem2.getTotalPrice()).thenReturn(155.0);

        Cart cart = new Cart();
        cart.setCartItems(List.of(cartItem, cartItem2));

        double result = cart.calculateTotalPrice();
        assertEquals(300, result);

    }
}