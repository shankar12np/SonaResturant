package com.example.sona.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "food_inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inventoryId;
    private String itemName;
    private Long quantity;
    private String dateMade;


}
