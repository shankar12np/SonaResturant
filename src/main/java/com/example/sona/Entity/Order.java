package com.example.sona.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "food_order")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String orderStatus;

    private String customerName;

}