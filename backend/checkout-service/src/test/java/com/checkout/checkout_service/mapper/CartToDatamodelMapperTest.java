package com.checkout.checkout_service.mapper;

import com.checkout.checkout_service.model.Cart;
import com.checkout.checkout_service.model.CartItem;
import com.checkout.checkout_service.repository.CartDataModel;
import com.checkout.checkout_service.repository.CartItemDataModel;
import com.checkout.checkout_service.repository.ItemDataModel;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static com.checkout.checkout_service.TestData.createCartItemDataModel;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CartToDatamodelMapperTest {

    CartToDatamodelMapper mapper = Mappers.getMapper(CartToDatamodelMapper.class);

    @Test
    void mapToModel() {
        CartDataModel cartDataModel = CartDataModel.builder().id(888L).build();
        ItemDataModel itemDataModel = ItemDataModel.builder()
                .id(999L)
                .name("Melon")
                .price(10.0)
                .isOffer(true)
                .offerPrice(20.0)
                .offerQuantity(3)
                .build();
        CartItemDataModel cartItemDataModel = createCartItemDataModel(cartDataModel, itemDataModel);

        cartDataModel.setCartItems(List.of(cartItemDataModel));

        Cart result = mapper.mapToModel(cartDataModel);
        CartItem cartItem = result.getCartItems().get(0);
        assertEquals(cartDataModel.getId(), result.getId());
        assertEquals(cartDataModel.getCartItems().get(0).getId() , cartItem.getId());
        assertEquals(cartDataModel.getCartItems().get(0).getQuantity() , cartItem.getQuantity());
        assertEquals(cartDataModel.getCartItems().get(0).getItem().getId() , cartItem.getItem().getId());
        assertEquals(cartDataModel.getCartItems().get(0).getItem().getName() , cartItem.getItem().getName());
        assertEquals(cartDataModel.getCartItems().get(0).getItem().getPrice() , cartItem.getItem().getPrice());
        assertEquals(cartDataModel.getCartItems().get(0).getItem().getIsOffer() , cartItem.getItem().getIsOffer());
        assertEquals(cartDataModel.getCartItems().get(0).getItem().getOfferPrice() , cartItem.getItem().getOfferPrice());
        assertEquals(cartDataModel.getCartItems().get(0).getItem().getOfferQuantity() , cartItem.getItem().getOfferQuantity());
    }


}