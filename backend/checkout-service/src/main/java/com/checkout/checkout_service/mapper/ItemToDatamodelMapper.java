package com.checkout.checkout_service.mapper;

import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.repository.ItemDataModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemToDatamodelMapper {

    ItemDataModel mapToDataModel(Item model);

    Item mapToModel(ItemDataModel dataModel);

}
