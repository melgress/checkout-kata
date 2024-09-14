package com.checkout.checkout_service.service;


import com.checkout.checkout_service.mapper.ItemToDatamodelMapper;
import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.repository.ItemDataModel;
import com.checkout.checkout_service.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckoutServiceImplTest {

    @Mock
    ItemRepository itemRepository;

    @Mock
    ItemToDatamodelMapper itemMapper;
    CheckoutService checkoutService;

    @BeforeEach
    public void setUp(){
        checkoutService = new CheckoutServiceImpl(itemRepository, itemMapper);
    }

    @Test
    public void getItems() {
        ItemDataModel item1 = ItemDataModel.builder().id(11L).build();
        ItemDataModel item2 = ItemDataModel.builder().id(22L).build();
        when(itemRepository.findAll()).thenReturn(List.of(item1, item2));

        List<Item> result = checkoutService.getItems();

        verify(itemRepository, times(1)).findAll();
        verify(itemMapper, times(1)).mapToModel(item1); // Ensure item1 was processed first
        verify(itemMapper, times(1)).mapToModel(item2); //
        assertThat(result).hasSize(2);


    }


}
