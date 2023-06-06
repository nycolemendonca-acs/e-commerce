package com.santana.java.back.end.dto;

import com.santana.java.back.end.model.Item;
import com.santana.java.back.end.model.Shop;

public class ConvertDTO {
    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setProductIdentifier(item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        return shopDTO;
    }
}
