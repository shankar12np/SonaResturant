package com.example.sona.Model;

import lombok.Data;

import java.util.List;
@Data
public class InventoryRequest {
    private String itemName;
    private Long quantity;
    private String dateMade;
    private List<InventoryRequest> subRequests;
}
