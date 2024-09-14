package com.checkout.checkout_service.api;

import com.checkout.checkout_service.api.response.CheckoutResponse;
import com.checkout.checkout_service.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CheckoutApi {

    @GetMapping("/items")
    ResponseEntity<List<Item>> getItems();

    @PostMapping("/cart")
    ResponseEntity<Void> addItemToCart(@RequestBody Item cartItem);

    @PostMapping("/checkout")
    ResponseEntity<CheckoutResponse> checkout();
}
