package controller;

import entity.PresentsToTribute;
import entity.Shop;
import entity.Tribute;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.PresentsToTributeService;
import service.ShopService;
import service.TributeService;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/hungergames")
@EnableAutoConfiguration
public class ShopController {

    @Autowired
    ShopService shopService;

    @Autowired
    UserService userService;

    @Autowired
    TributeService tributeService;

    @Autowired
    PresentsToTributeService presentsToTributeService;

    @Secured("ROLE_USER")
    @GetMapping( "/shop")
    public @ResponseBody ResponseEntity getShopList() {
        List<Shop> shopList = shopService.getFullRange();
        return ResponseEntity.status(HttpStatus.OK).body(shopList);
    }

    @Secured("ROLE_USER")
    @GetMapping( "/products_by_types")
    public @ResponseBody ResponseEntity getProductsByType(@RequestParam("type") String type) {
        List<Shop> products = shopService.getProductsByTypeOfPresent(type);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}