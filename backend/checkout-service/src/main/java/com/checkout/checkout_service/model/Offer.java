package com.checkout.checkout_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Offer {

    private String itemName;
    private double discountPrice;
    private int quantityRequired;
    private String offerDescription;

}
