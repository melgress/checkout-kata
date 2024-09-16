package com.checkout.checkout_service.mapper;

import com.checkout.checkout_service.model.Item;
import com.checkout.checkout_service.repository.ItemDataModel;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static com.checkout.checkout_service.TestData.createItemDataModel;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemToDatamodelMapperTest {

    ItemToDatamodelMapper mapper = Mappers.getMapper(ItemToDatamodelMapper.class);

    @Test
    void mapToModel() {
        ItemDataModel itemDataModel = createItemDataModel();

        Item result = mapper.mapToModel(itemDataModel);
        assertEquals(itemDataModel.getId(), result.getId());
        assertEquals(itemDataModel.getName(), result.getName());
        assertEquals(itemDataModel.getPrice(), result.getPrice());
        assertEquals(itemDataModel.getIsOffer(), result.getIsOffer());
        assertEquals(itemDataModel.getOfferQuantity(), result.getOfferQuantity());
        assertEquals(itemDataModel.getOfferPrice(), result.getOfferPrice());

    }


}