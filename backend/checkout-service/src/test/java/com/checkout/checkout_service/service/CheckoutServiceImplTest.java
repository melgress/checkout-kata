package com.checkout.checkout_service.service;


import com.checkout.checkout_service.mapper.CartItemToDatamodelMapper;
import com.checkout.checkout_service.mapper.CartToDatamodelMapper;
import com.checkout.checkout_service.mapper.ItemToDatamodelMapper;
import com.checkout.checkout_service.model.CartItem;
import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceImplTest {

    @Mock
    ItemRepository itemRepository;

    ItemToDatamodelMapper itemMapper = Mappers.getMapper(ItemToDatamodelMapper.class);

    @Mock
    CartRepository cartRepository;

    CartItemToDatamodelMapper cartItemToDatamodelMapper = Mappers.getMapper(CartItemToDatamodelMapper.class)    ;

    CartToDatamodelMapper cartToDatamodelMapper = Mappers.getMapper(CartToDatamodelMapper.class);

    @Mock
    CartItemRepository cartItemRepository;


    CheckoutService checkoutService;

    @BeforeEach
    public void setUp(){
        checkoutService = new CheckoutServiceImpl(itemRepository, itemMapper, cartRepository, cartItemToDatamodelMapper, cartToDatamodelMapper, cartItemRepository);
    }

    @Test
    public void getItems() {
        ItemDataModel item1 = ItemDataModel.builder().id(11L).build();
        ItemDataModel item2 = ItemDataModel.builder().id(22L).build();
        when(itemRepository.findAll()).thenReturn(List.of(item1, item2));

        List<Item> result = checkoutService.getItems();

        verify(itemRepository, times(1)).findAll();
        assertThat(result).hasSize(2);

    }

    @Test
    void addItemToCartWhenCartExistsAndItemExists() {
        Long cartId =55L;
        Long itemId = 66L;
        int quantity = 5;

        CartDataModel cartDataModel = new CartDataModel();

        Item item = Item.builder().build();
        item.setId(itemId);
        ItemDataModel itemDataModel = new ItemDataModel();

        CartItemDataModel cartItemDataModel = new CartItemDataModel();
        cartItemDataModel.setCart(CartDataModel.builder().id(cartId).build());
        cartItemDataModel.setItem(ItemDataModel.builder().id(itemId).build());
        cartItemDataModel.setQuantity(3);

        CartItem cartItem = new CartItem();
        cartItem.setCartId(cartId);
        cartItem.setItem(item);
        cartItem.setQuantity(3);

        when(cartRepository.findById(cartId)).thenReturn(Optional.of(cartDataModel));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(itemDataModel));
        when(cartItemRepository.findByCartIdAndItemId(cartId, itemId)).thenReturn(Optional.of(cartItemDataModel));

        checkoutService.addItemToCart(cartId, itemId, quantity);

        ArgumentCaptor<CartItemDataModel> cartItemCaptor = ArgumentCaptor.forClass(CartItemDataModel.class);
        verify(cartItemRepository).save(cartItemCaptor.capture());

        CartItemDataModel savedCartItem = cartItemCaptor.getValue();
        assertEquals(cartId, savedCartItem.getCart().getId());
        assertEquals(itemId, savedCartItem.getItem().getId());
        assertEquals(8, savedCartItem.getQuantity());
    }


    @Test
    void addItemToCartWhenCartDoesNotExistAndItemExists() {
        Long cartId = 111L;
        Long itemId = 222L;
        int quantity = 10;

        CartDataModel newCartDataModel = new CartDataModel();
        newCartDataModel.setId(cartId);

        Item item = Item.builder().id(itemId).build();
        ItemDataModel itemDataModel = new ItemDataModel();
        itemDataModel.setId(itemId);

        CartItem cartItem = new CartItem();
        cartItem.setCartId(cartId);
        cartItem.setItem(item);
        cartItem.setQuantity(quantity);

        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());
        when(cartRepository.save(any(CartDataModel.class))).thenReturn(newCartDataModel);
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(itemDataModel));
        when(cartItemRepository.findByCartIdAndItemId(cartId, itemId)).thenReturn(Optional.empty());

        checkoutService.addItemToCart(cartId, itemId, quantity);

        ArgumentCaptor<CartItemDataModel> cartItemCaptor = ArgumentCaptor.forClass(CartItemDataModel.class);
        verify(cartItemRepository).save(cartItemCaptor.capture());

        CartItemDataModel savedCartItem = cartItemCaptor.getValue();
        assertEquals(cartId, savedCartItem.getCart().getId());
        assertEquals(itemId, savedCartItem.getItem().getId());
        assertEquals(quantity, savedCartItem.getQuantity());
    }
}



