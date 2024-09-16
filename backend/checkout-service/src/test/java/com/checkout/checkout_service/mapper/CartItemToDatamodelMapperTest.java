package com.checkout.checkout_service.mapper;

import com.checkout.checkout_service.model.CartItem;
import com.checkout.checkout_service.repository.CartDataModel;
import com.checkout.checkout_service.repository.CartItemDataModel;
import com.checkout.checkout_service.repository.ItemDataModel;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.checkout.checkout_service.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartItemToDatamodelMapperTest {

    CartItemToDatamodelMapper mapper = Mappers.getMapper(CartItemToDatamodelMapper.class);

    @Test
    void mapToDataModel() {
        CartItem cartItem = createCartItem();

        CartItemDataModel result = mapper.mapToDataModel(cartItem);
        assertEquals(cartItem.getId(), result.getId());
        assertEquals(cartItem.getCartId(), result.getCart().getId());
        assertEquals(cartItem.getItem().getId(), result.getItem().getId());
        assertEquals(cartItem.getItem().getName(), result.getItem().getName());
        assertEquals(cartItem.getItem().getPrice(), result.getItem().getPrice());
        assertEquals(cartItem.getItem().getIsOffer(), result.getItem().getIsOffer());
        assertEquals(cartItem.getItem().getOfferPrice(), result.getItem().getOfferPrice());
        assertEquals(cartItem.getItem().getOfferQuantity(), result.getItem().getOfferQuantity());
        assertEquals(cartItem.getQuantity(), result.getQuantity());
    }

    @Test
    void mapToModel() {
        CartDataModel cartDataModel = CartDataModel.builder().id(888L).build();
        ItemDataModel itemDataModel = createItemDataModel();
        CartItemDataModel cartItemDataModel = createCartItemDataModel(cartDataModel, itemDataModel);

        CartItem result = mapper.mapToModel(cartItemDataModel);
        assertEquals(cartItemDataModel.getId(), result.getId());
        assertEquals(cartItemDataModel.getCart().getId(), result.getCartId());
        assertEquals(cartItemDataModel.getItem().getId(), result.getItem().getId());
        assertEquals(cartItemDataModel.getItem().getName(), result.getItem().getName());
        assertEquals(cartItemDataModel.getItem().getPrice(), result.getItem().getPrice());
        assertEquals(cartItemDataModel.getItem().getIsOffer(), result.getItem().getIsOffer());
        assertEquals(cartItemDataModel.getItem().getOfferPrice(), result.getItem().getOfferPrice());
        assertEquals(cartItemDataModel.getItem().getOfferQuantity(), result.getItem().getOfferQuantity());
        assertEquals(cartItemDataModel.getQuantity(), result.getQuantity());
    }


}