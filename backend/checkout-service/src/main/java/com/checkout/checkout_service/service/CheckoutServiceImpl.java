package com.checkout.checkout_service.service;

import com.checkout.checkout_service.api.response.CartItemResponse;
import com.checkout.checkout_service.api.response.CheckoutResponse;
import com.checkout.checkout_service.mapper.CartItemToDatamodelMapper;
import com.checkout.checkout_service.mapper.CartToDatamodelMapper;
import com.checkout.checkout_service.mapper.ItemToDatamodelMapper;
import com.checkout.checkout_service.model.Cart;
import com.checkout.checkout_service.model.CartItem;
import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final ItemRepository itemRepository;

    private final ItemToDatamodelMapper itemMapper;

    private final CartRepository cartRepository;

    private final CartItemToDatamodelMapper cartItemToDatamodelMapper;

    private final CartToDatamodelMapper cartToDatamodelMapper;

    private final CartItemRepository cartItemRepository;


    public CheckoutServiceImpl(ItemRepository itemRepository,
                               ItemToDatamodelMapper itemMapper,
                               CartRepository cartRepository,
                               CartItemToDatamodelMapper cartItemToDatamodelMapper,
                               CartToDatamodelMapper cartToDatamodelMapper,
                               CartItemRepository cartItemRepository) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.cartRepository = cartRepository;
        this.cartItemToDatamodelMapper = cartItemToDatamodelMapper;
        this.cartToDatamodelMapper = cartToDatamodelMapper;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public void addItemToCart(Long cartId, Long itemId, int quantity) {
        Cart cart = cartToDatamodelMapper.mapToModel(cartRepository.findById(cartId).orElseGet(() -> {
            CartDataModel newCart = new CartDataModel();
            return cartRepository.save(newCart);
        }));

        Item item = itemMapper.mapToModel(itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found")));

        Optional<CartItemDataModel> cartItemDataModel = cartItemRepository.findByCartIdAndItemId(cartId, itemId);

        if (cartItemDataModel.isPresent()) {
            CartItem cartItem = cartItemToDatamodelMapper.mapToModel(cartItemDataModel.get());
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItemToDatamodelMapper.mapToDataModel(cartItem));
        } else {
            CartItem cartItem = CartItem.builder().cartId(cart.getId()).item(item).quantity(quantity).build();
            cartItemRepository.save(cartItemToDatamodelMapper.mapToDataModel(cartItem));
        }
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll().stream().map(itemMapper::mapToModel).toList();
    }

    @Override
    public CheckoutResponse checkout(long cartId) {
        Cart cart = cartToDatamodelMapper.mapToModel(cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found")));

        List<CartItemResponse> itemResponses = Optional.ofNullable(cart.getCartItems())
                .orElse(Collections.emptyList())
                .stream()
                .map(cartItem -> new CartItemResponse(
                        cartItem.getItem().getName(),
                        cartItem.getItem().getPrice(),
                        cartItem.getQuantity(),
                        cartItem.getTotalPrice()
                ))
                .collect(Collectors.toList());

        double totalCartPrice = Optional.ofNullable(cart.getCartItems())
                .filter(cartItems -> !cartItems.isEmpty())
                .map(cartItems -> cart.calculateTotalPrice())
                .orElse(0.00);

        return new CheckoutResponse(itemResponses, totalCartPrice);
    }
}
