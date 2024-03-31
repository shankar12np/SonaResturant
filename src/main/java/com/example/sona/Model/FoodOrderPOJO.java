package com.example.sona.Model;

import com.example.sona.Entity.LineItems;
import com.example.sona.Entity.Order;
import lombok.Data;

import java.util.List;

@Data
public class FoodOrderPOJO {
    private String orderId;
    private String orderStatus;
    private String customerName;

    private List<LineItems> lineItems;
}
