package com.example.sona.Model;

import lombok.Data;

@Data
public class LineItemsPOJO {
    private String itemId;
    private String itemTitle;
    private Float price;
    private String orderId;
}
