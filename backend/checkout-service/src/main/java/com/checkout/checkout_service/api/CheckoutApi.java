package com.checkout.checkout_service.api;

import com.checkout.checkout_service.api.response.CheckoutResponse;
import com.checkout.checkout_service.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CheckoutApi {

    @GetMapping("/items")
    ResponseEntity<List<Item>> getItems();

    @PostMapping("/{cartId}/add")
    ResponseEntity<Void> addItemToCart(@PathVariable Long cartId,
                                       @RequestParam Long itemId,
                                       @RequestParam int quantity);

    @PostMapping("/checkout/{cartId}")
    ResponseEntity<CheckoutResponse> checkout(@PathVariable long cartId);
}
