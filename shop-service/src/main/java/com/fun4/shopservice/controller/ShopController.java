package com.fun4.shopservice.controller;

import com.fun4.shopservice.manager.ShopManager;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(value="/shops",description="Shop Service",produces ="application/json")
@RequestMapping("/shops")
public class ShopController {
    ShopManager shopManager;

    public ShopController() {
        this.shopManager = new ShopManager();
    }


    // Get shops
    @GetMapping()
    public ResponseEntity getAllShops(
            @RequestParam(value = "startIndex", required = false) Integer startIndex,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ){
        return ResponseEntity.status(HttpStatus.OK).body(this.shopManager.getShops(startIndex, pageSize));
    }
}
