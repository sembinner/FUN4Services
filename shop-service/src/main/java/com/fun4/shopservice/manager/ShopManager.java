package com.fun4.shopservice.manager;

import com.fun4.shopservice.model.Shop;
import com.fun4.shopservice.repository.ShopRepository;

import java.util.List;

public class ShopManager {
    private ShopRepository shopRepository;

    public ShopManager() {
        this.shopRepository = new ShopRepository();
    }

    public List<Shop> getShops(Integer startIndex, Integer pageSize){return this.shopRepository.getShops(startIndex, pageSize);}
}
