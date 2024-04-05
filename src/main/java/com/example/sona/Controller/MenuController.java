package com.example.sona.Controller;

import com.example.sona.Model.MenuAddResponse;
import com.example.sona.Model.MenuDTO;
import com.example.sona.Model.MenuPriceResponse;
import com.example.sona.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
   @PostMapping("/add-new-item")
    public ResponseEntity<?> addMenuItem(@RequestBody MenuAddResponse menuAddResponse){
      MenuDTO menuDTO =  menuService.addMenuItems(menuAddResponse);
        return ResponseEntity.accepted().body(menuDTO);
    }
    @PostMapping("/add-many")
    public ResponseEntity<?>addManyItems(@RequestBody List <MenuAddResponse> menuAddResponse){
     List <MenuDTO> menuDTO =  menuService.addMultipleItem(menuAddResponse);
       return ResponseEntity.ok(menuDTO);
    }
    @PatchMapping("/update-price/{menuId}")
    public ResponseEntity<?> updatePrice(@RequestBody MenuPriceResponse menuPriceResponse, @PathVariable String menuId){
      MenuPriceResponse menuData =  menuService.updatePrice(menuPriceResponse,menuId);
       return ResponseEntity.ok(menuData);
    }
}
