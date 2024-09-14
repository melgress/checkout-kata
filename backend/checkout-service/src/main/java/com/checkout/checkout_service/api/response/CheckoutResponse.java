package com.checkout.checkout_service.api.response;

import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.model.Offer;

import java.util.List;

public record CheckoutResponse(List<Item> items, double totalPrice, List<Offer> applicableOffers) {
}
