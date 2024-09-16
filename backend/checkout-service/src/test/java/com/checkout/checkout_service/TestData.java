package com.checkout.checkout_service;

import com.checkout.checkout_service.model.CartItem;
import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.repository.CartDataModel;
import com.checkout.checkout_service.repository.CartItemDataModel;
import com.checkout.checkout_service.repository.ItemDataModel;

public class TestData {

    public static Item createItem() {
        return Item.builder()
                .id(22L)
                .name("Apple")
                .price(30.0)
                .isOffer(Boolean.TRUE)
                .offerPrice(45.0)
                .offerQuantity(2)
                .build();
    }

    public static CartItem createCartItem() {
        return CartItem.builder()
                .cartId(12L)
                .item(createItem())
                .quantity(5)
                .build();
    }

    public static ItemDataModel createItemDataModel() {
        return ItemDataModel.builder()
                .id(999L)
                .name("Melon")
                .price(10.0)
                .isOffer(true)
                .offerPrice(20.0)
                .offerQuantity(3)
                .build();
    }

    public static CartItemDataModel createCartItemDataModel(
            CartDataModel cartDataModel, ItemDataModel itemDataModel) {
        return CartItemDataModel.builder()
                .id(12L)
                .cart(cartDataModel)
                .item(itemDataModel)
                .quantity(7)
                .build();
    }
}
