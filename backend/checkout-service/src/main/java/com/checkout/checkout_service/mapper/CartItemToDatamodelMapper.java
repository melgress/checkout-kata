package com.checkout.checkout_service.mapper;

import com.checkout.checkout_service.model.CartItem;
import com.checkout.checkout_service.repository.CartItemDataModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartItemToDatamodelMapper {

    @Mapping(source = "cartId", target = "cart.id")
    CartItemDataModel mapToDataModel(CartItem model);

    @Mapping(source = "cart.id", target = "cartId")
    CartItem mapToModel(CartItemDataModel dataModel);

}
