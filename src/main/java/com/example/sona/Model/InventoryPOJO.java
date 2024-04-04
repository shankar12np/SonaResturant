package com.example.sona.Model;

import lombok.Data;

import java.util.List;

@Data
public class InventoryPOJO {
    private String inventoryId;
    private String itemName;
    private Long quantity;
    private String dateMade;

    private List<InventoryRequest>inventoryRequestList;
}
