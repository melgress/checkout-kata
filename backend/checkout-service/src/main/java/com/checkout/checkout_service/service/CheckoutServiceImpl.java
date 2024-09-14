package com.checkout.checkout_service.service;

import com.checkout.checkout_service.api.response.CheckoutResponse;
import com.checkout.checkout_service.mapper.ItemToDatamodelMapper;
import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final ItemRepository itemRepository;

    private final ItemToDatamodelMapper itemMapper;

    public CheckoutServiceImpl(final ItemRepository itemRepository, ItemToDatamodelMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    @Override
    public void addItemToCart(Item cartItem) {

    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll().stream().map(itemMapper::mapToModel).toList();
    }

    @Override
    public CheckoutResponse checkout() {
        return null;
    }
}
