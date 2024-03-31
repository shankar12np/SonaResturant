package com.example.sona.Model;

import lombok.Data;

import java.util.List;

@Data
public class PlaceFoodOrderRequest {
    private CustomerRequestPOJO customerRequest;
    private List<LineItemsRequests> lineItemsRequests;



}
