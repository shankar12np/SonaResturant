package com.example.sona.Controller;

import com.example.sona.Entity.LineItems;
import com.example.sona.Entity.Order;
import com.example.sona.Model.*;
import com.example.sona.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<FoodOrderPOJO> placeOrder(@RequestBody PlaceFoodOrderRequest placeFoodOrderRequest) {
        try {
            FoodOrderPOJO foodOrder = orderService.placeOrder(placeFoodOrderRequest);
            return ResponseEntity.accepted().body(foodOrder);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(("/get-all-orders"))
    public ResponseEntity<List<Order>> getAllOrder() {
        List<Order> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/get-line-items")
    public ResponseEntity<List<LineItems>> getAllLineItems(){
        List<LineItems> data = orderService.getAllLineItems();
        return ResponseEntity.ok(data);

    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<Order> findOrderByOrderId(@PathVariable String orderId) {
        Order orderData = orderService.getOrderByOrderId(orderId);
        return ResponseEntity.ok(orderData);
    }



}
