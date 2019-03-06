package com.fun4.shopservice.controller;

import com.fun4.shopservice.manager.ShopManager;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(value="/products",description="Shop Service",produces ="application/json")
@RequestMapping("/products")
public class ShopController {
    ShopManager shopManager;

    public ShopController() {
        this.shopManager = new ShopManager();
    }


}
