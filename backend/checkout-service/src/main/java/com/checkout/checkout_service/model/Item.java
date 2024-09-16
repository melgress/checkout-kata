package com.checkout.checkout_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

    private Long id;

    private String name;

    private double price;

    private Boolean isOffer;

    private Integer offerQuantity;

    private Double offerPrice;

}
