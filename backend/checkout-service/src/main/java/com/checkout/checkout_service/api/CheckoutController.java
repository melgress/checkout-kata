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
    @PostMapping("/cart")
    public ResponseEntity<Void> addItemToCart(@RequestBody Item cartItem) {
        checkoutService.addItemToCart(cartItem);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cart")
    public ResponseEntity<List<Item>> getCartItems() {
        List<Item> items = checkoutService.getCartItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponse> checkout() {
        CheckoutResponse response = checkoutService.checkout();
        return ResponseEntity.ok(response);
    }
}
