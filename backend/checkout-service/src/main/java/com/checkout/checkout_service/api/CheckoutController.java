package com.checkout.checkout_service.api;

import com.checkout.checkout_service.api.response.CheckoutResponse;
import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.service.CheckoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CheckoutController implements CheckoutApi{

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItems() {
        List<Item> items = checkoutService.getItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/cart/{cartId}")
    public ResponseEntity<Void> addItemToCart(@PathVariable Long cartId,
                                              @RequestParam Long itemId,
                                              @RequestParam int quantity) {
       checkoutService.addItemToCart(cartId, itemId, quantity);
       return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout/{cartId}")
    public ResponseEntity<CheckoutResponse> checkout(@PathVariable long cartId) {
        CheckoutResponse response = checkoutService.checkout(cartId);
        return ResponseEntity.ok(response);
    }
}
