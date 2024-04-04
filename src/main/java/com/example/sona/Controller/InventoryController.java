package com.example.sona.Controller;

import com.example.sona.Entity.Inventory;
import com.example.sona.Model.InventoryPOJO;
import com.example.sona.Model.InventoryRequest;
import com.example.sona.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;


    @PostMapping("/add-inventory/{orderId}")
    public ResponseEntity<?> addInventory(@RequestBody List<InventoryRequest> inventoryRequestList, @PathVariable String orderId) {
        List<Inventory> inventory = inventoryService.createAndSaveInventory(inventoryRequestList, orderId);
        return ResponseEntity.ok(inventory);
    }
@GetMapping("/get-all-inventory")
    public ResponseEntity<?> getAllInventory(@RequestBody Inventory inventory, List<InventoryRequest> inventoryRequestList){
      InventoryPOJO inventoryPOJO =  inventoryService.getCurrentInventory(inventory, inventoryRequestList);
       return ResponseEntity.ok(inventoryPOJO);
    }
}
