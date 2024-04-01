package com.example.sona.Model;

import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private String orderId;
    private String newStatus;
}
