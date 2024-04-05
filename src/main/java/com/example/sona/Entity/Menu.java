package com.example.sona.Entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuId;
    private String menuItemName;
    private String price;
    private Integer quantity;




}
