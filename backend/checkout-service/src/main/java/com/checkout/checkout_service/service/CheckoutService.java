package com.checkout.checkout_service.service;

import com.checkout.checkout_service.api.response.CheckoutResponse;
import com.checkout.checkout_service.model.Item;

import java.util.List;

public interface CheckoutService {
    void addItemToCart(Item cartItem);

    List<Item> getItems();

    CheckoutResponse checkout();
}
