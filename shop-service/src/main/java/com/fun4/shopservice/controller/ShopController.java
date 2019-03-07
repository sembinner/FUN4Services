package com.fun4.shopservice.controller;

import com.fun4.shopservice.manager.ShopManager;
import com.fun4.shopservice.model.Shop;
import com.fun4.shopservice.viewmodel.CreateShopViewModel;
import com.fun4.shopservice.viewmodel.UpdateShopViewModel;
import io.swagger.annotations.Api;
import org.apache.coyote.http11.AbstractHttp11JsseProtocol;
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

    // Get shop - by id
    @GetMapping("/{shopId}")
    public ResponseEntity getShopById (@PathVariable(value = "shopId") int shopId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.shopManager.getShopById(shopId));
    }

    // Create new Shop
    @PostMapping()
    public ResponseEntity addShop(CreateShopViewModel viewModel){
        System.out.println(viewModel + viewModel.getName() + viewModel.getDescription() + viewModel.getUserId());
        Shop shop = new Shop(viewModel.getName(), viewModel.getDescription(), viewModel.getUserId());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.shopManager.addShop(shop));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong, shop not added.");
        }
    }

    // Update shop
    @PutMapping("/{id}")
    public ResponseEntity updateShop(UpdateShopViewModel viewmodel){
        Shop shop = this.shopManager.getShopById(viewmodel.getId());

        shop.updateShop(viewmodel.getName(), viewmodel.getDescription());

        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.shopManager.updateShop(shop));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong while updating the shop");
        }
    }

    // Delete a product - by id
    @DeleteMapping("/{shopId}")
    public ResponseEntity deleteShop(@PathVariable(value = "shopId") int shopId){
        try {
            this.shopManager.deleteShop(shopId);
            return ResponseEntity.status(HttpStatus.OK).body("Shop has been deleted");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Shop was not deleted");
        }
    }
}
