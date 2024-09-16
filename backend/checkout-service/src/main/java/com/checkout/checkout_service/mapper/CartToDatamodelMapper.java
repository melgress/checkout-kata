package com.checkout.checkout_service.mapper;

import com.checkout.checkout_service.model.Cart;
import com.checkout.checkout_service.repository.CartDataModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartToDatamodelMapper {

    Cart mapToModel(CartDataModel dataModel);

}
