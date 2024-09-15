package com.checkout.checkout_service.api.response;

import lombok.Builder;

@Builder
public record CartItemResponse(
        String itemName,
        double itemPrice,
        int quantity,
        double totalItemPrice) {

}