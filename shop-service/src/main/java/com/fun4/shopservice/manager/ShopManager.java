package com.fun4.shopservice.manager;

import com.fun4.shopservice.repository.ShopRepository;

public class ShopManager {
    private ShopRepository shopRepository;

    public ShopManager() {
        this.shopRepository = new ShopRepository();
    }
}
