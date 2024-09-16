package com.checkout.checkout_service.repository;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "cart")
public class CartDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<CartItemDataModel> cartItems = new ArrayList<>();


}
