package com.checkout.checkout_service.api;

import com.checkout.checkout_service.api.response.CartItemResponse;
import com.checkout.checkout_service.api.response.CheckoutResponse;
import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.service.CheckoutService;
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

    @MockBean
    private CheckoutService checkoutService;

    @Test
    public void itShouldTestAddItemToCart() throws Exception {
        Long cartId = 456L;
        Long itemId = 123L;
        int quantity = 3;

        mockMvc.perform(post("/api/v1/cart/{cartId}/add", cartId)
                        .param("itemId", String.valueOf(itemId))
                        .param("quantity", String.valueOf(quantity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void itShouldTestGetItems() throws Exception {
        Item item1 = Item.builder().name("Banana").price(2.50).build();
        Item item2 = Item.builder().name("Melon").price(4.50).build();
        when(checkoutService.getItems()).thenReturn(List.of(item1, item2));

        mockMvc.perform(get("/api/v1/items")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Banana"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(2.50))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Melon"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(4.50));
    }

    @Test
    public void shouldAddItemToCart() throws Exception {
        mockMvc.perform(post("/api/v1/cart/1/add")
                        .param("itemId", "1")
                        .param("quantity", "3"))
                .andExpect(status().isNoContent());
    }


    @Test
    public void itShouldTestCheckout() throws Exception {
        long cartId = 23L;
        CheckoutResponse checkoutResponse =
                CheckoutResponse.builder().items(
                        List.of(CartItemResponse.builder()
                                .itemName("Apple")
                                .itemPrice(20.0)
                                .quantity(5)
                                .totalItemPrice(100.0)
                                .build()))
                        .totalPrice(100.0)
                        .build();
        when(checkoutService.checkout(cartId)).thenReturn(checkoutResponse);

        mockMvc.perform(post("/api/v1/checkout/23")
                        .accept(MediaType.APPLICATION_JSON)
                .param("itemId", "134")
                .param("quantity", "890"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].itemName").value("Apple"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].itemPrice").value("20.0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].quantity").value("5"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.items[0].totalItemPrice").value("100.0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice").value("100.0"));
    }
}