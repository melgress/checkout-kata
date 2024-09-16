package com.checkout.checkout_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CartItem {

    private Long id;
    private long cartId;
    private Item item;
    private int quantity;

    public double getTotalPrice() {
        if (Boolean.TRUE.equals(item.getIsOffer()) && quantity >= item.getOfferQuantity()) {
            int offerSets = quantity / item.getOfferQuantity();
            int remainingItems = quantity % item.getOfferQuantity();
            return (offerSets * item.getOfferPrice()) + (remainingItems * item.getPrice());
        } else {
            return quantity * item.getPrice();
        }
    }
}
