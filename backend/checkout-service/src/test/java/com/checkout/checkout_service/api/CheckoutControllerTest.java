package com.checkout.checkout_service.api;

import com.checkout.checkout_service.api.response.CheckoutResponse;
import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.model.Offer;
import com.checkout.checkout_service.service.CheckoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CheckoutController.class)
public class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CheckoutService cartService;

    @Test
    public void itShouldTestAddItemToCart() throws Exception {
        Item cartItem = Item.builder().name("Apple").price(3.00).build();

        mockMvc.perform(post("/api/v1/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartItem)))
                .andExpect(status().isOk());
    }

    @Test
    public void itShouldTestGetItems() throws Exception {
        Item item1 = Item.builder().name("Banana").price(2.50).build();
        Item item2 = Item.builder().name("Melon").price(4.50).build();
        when(cartService.getItems()).thenReturn(List.of(item1, item2));

        mockMvc.perform(get("/api/v1/items")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Banana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(2.50))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Melon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(4.50));
    }

    @Test
    public void itShouldTestCheckout() throws Exception {
        Item item1 = Item.builder().name("Banana").price(2.50).build();
        Item item2 = Item.builder().name("Melon").price(4.50).build();
        Offer offer = Offer.builder().itemName("Banana").discountPrice(130.0).quantityRequired(3).offerDescription("3 for 130").build();
        CheckoutResponse response = new CheckoutResponse(List.of(item1, item2), 19.0, List.of(offer));

        when(cartService.checkout()).thenReturn(response);

        mockMvc.perform(post("/api/v1/checkout")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].name").value("Banana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[1].name").value("Melon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value(19.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.applicableOffers[0].offerDescription").value("3 for 130"));
    }
}