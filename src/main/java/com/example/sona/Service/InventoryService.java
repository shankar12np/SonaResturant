package com.example.sona.Service;

import com.example.sona.Entity.Inventory;
import com.example.sona.Model.InventoryPOJO;
import com.example.sona.Model.InventoryRequest;
import com.example.sona.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;


    public InventoryPOJO getCurrentInventory(Inventory inventory, List<InventoryRequest> inventoryRequestList) {
        InventoryPOJO inventoryPOJO = new InventoryPOJO();
        inventoryPOJO.setInventoryId(inventory.getInventoryId());
        inventoryPOJO.setItemName(inventory.getItemName());
        inventoryPOJO.setQuantity(inventory.getId());
        inventoryPOJO.setDateMade(inventory.getDateMade());
        inventoryPOJO.setInventoryRequestList(inventoryRequestList);
        return inventoryPOJO;

    }

    public List<Inventory> createAndSaveInventory(List<InventoryRequest> inventoryRequestList, String orderId) {
        List<Inventory> savedInventoryList = new ArrayList<>();
        saveInventoryRecursively(inventoryRequestList, orderId, savedInventoryList);
        return savedInventoryList;
    }

    private void saveInventoryRecursively(List<InventoryRequest> inventoryRequestList, String orderId, List<Inventory> savedInventoryList) {
        for (InventoryRequest inventoryRequest : inventoryRequestList) {
            Inventory inventory = new Inventory();
            inventory.setInventoryId(UUID.randomUUID().toString());
            inventory.setItemName(inventoryRequest.getItemName());
            inventory.setQuantity(inventoryRequest.getQuantity());
            inventory.setDateMade(inventoryRequest.getDateMade());

            inventoryRepository.save(inventory);
            savedInventoryList.add(inventory);

            if (inventoryRequest.getSubRequests() != null && !inventoryRequest.getSubRequests().isEmpty()) {
                saveInventoryRecursively(inventoryRequest.getSubRequests(), orderId, savedInventoryList);


            }

        }
    }

}
