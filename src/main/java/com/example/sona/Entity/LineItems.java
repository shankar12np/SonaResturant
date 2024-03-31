package com.example.sona.Entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "order_lines")
public class LineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemId;
    private String itemTitle;
    private Float price;
    private String orderId;
}
