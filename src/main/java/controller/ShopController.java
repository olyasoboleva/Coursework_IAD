package controller;

import entity.PresentsToTribute;
import entity.Shop;
import entity.Tribute;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping( "/shop")
    public @ResponseBody ResponseEntity getShopList() {
        List<Shop> shopList = shopService.getFullRange();
        return ResponseEntity.status(HttpStatus.OK).body(shopList);
    }

    @PostMapping("/send_present")
    public @ResponseBody ResponseEntity createPresentToTribute(Integer tributeID, Integer presentID, int quantity){
        User sender = userService.getUserByNick( SecurityContextHolder.getContext().getAuthentication().getName());
        Tribute tribute = tributeService.getTributeById(tributeID);
        Shop present = shopService.getProductById(presentID);
        PresentsToTribute presentsToTribute = new PresentsToTribute(present, tribute, sender, quantity);
        if (presentsToTributeService.createPresentsToTributes(presentsToTribute)==null){
            return ResponseEntity.status(HttpStatus.OK).body("Недостаточно средств");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Подарок отправлен");
        }
    }
}