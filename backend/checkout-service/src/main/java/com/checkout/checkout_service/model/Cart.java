package com.checkout.checkout_service.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private Long id;
    private List<Item> items = new ArrayList<>();
}
