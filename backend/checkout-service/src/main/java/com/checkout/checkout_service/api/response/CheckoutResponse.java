package com.checkout.checkout_service.api.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CheckoutResponse(List<CartItemResponse> items, double totalPrice) {
}
