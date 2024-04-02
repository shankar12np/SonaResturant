package com.example.sona.Service;

import com.example.sona.Entity.Customer;
import com.example.sona.Entity.LineItems;
import com.example.sona.Entity.Order;
import com.example.sona.Model.*;
import com.example.sona.Repository.CustomerRepository;
import com.example.sona.Repository.LineItemsRepository;
import com.example.sona.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LineItemsRepository lineItemsRepository;
    @Autowired
    private OrderRepository orderRepository;

    public FoodOrderPOJO placeOrder(PlaceFoodOrderRequest placeFoodOrderRequest) {
        Order order = createAndSaveOrder(placeFoodOrderRequest.getCustomerRequest());

        // Create and save line items entities
        List<LineItems> lineItems = createAndSaveLineItems(placeFoodOrderRequest.getLineItemsRequests(), order.getOrderId());

        // Create and return a response object
        return createFoodOrderResponse(order, lineItems);
    }


    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public List<LineItems> getAllLineItems() {
        return lineItemsRepository.findAll();
    }

    public Order getOrderByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public void updateOrderStatus(UpdateOrderStatusRequest request) {
        Order order = orderRepository.findByOrderId(request.getOrderId());
        if (order != null) {
            order.setOrderStatus(request.getNewStatus());
            orderRepository.save(order);
        }
    }

    public void cancelOrder(CancelOrderResponse response) {
        Order order = orderRepository.findByOrderId(response.getOrderId());
        if (order != null) {
            order.setOrderStatus("Cancelled");
            orderRepository.save(order);
        }
    }

    public void deleteOrder(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        if (order != null) {
            orderRepository.delete(order);
        } else {
            throw new RuntimeException("Order does not exist");
        }

    }


    private Order createAndSaveOrder(CustomerRequestPOJO customerRequest) {
        // Check if the customer already exists in the database
        Customer existingCustomer = customerRepository.findByCustomerId(customerRequest.getCustomerId());
        if (existingCustomer == null) {
            Customer newCustomer = new Customer();
            newCustomer.setCustomerId(customerRequest.getCustomerId());
            newCustomer.setCustomerName(customerRequest.getCustomerName());
            customerRepository.save(newCustomer);
        }

        // Fetch the customer from the database (either existing or newly created)
        Customer customer = customerRepository.findByCustomerId(customerRequest.getCustomerId());

        // Create and populate the order entity
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setCustomerName(customerRequest.getCustomerName());
        order.setOrderStatus("ACCEPTED");

        // Save the order entity to the database
        return orderRepository.save(order);
    }

    private List<LineItems> createAndSaveLineItems(List<LineItemsRequests> lineItemsRequests, String orderId) {
        List<LineItems> lineItemsList = new ArrayList<>();
        for (LineItemsRequests lineItemsRequest : lineItemsRequests) {
            // Create and populate the line item entity
            LineItems lineItem = new LineItems();
            lineItem.setItemId(UUID.randomUUID().toString());
            lineItem.setItemTitle(lineItemsRequest.getItemTitle());
            lineItem.setPrice(lineItemsRequest.getPrice());
            lineItem.setOrderId(orderId);

            // Save the line item entity to the database
            lineItemsRepository.save(lineItem);

            // Add the line item entity to the list
            lineItemsList.add(lineItem);
        }
        return lineItemsList;
    }

    private FoodOrderPOJO createFoodOrderResponse(Order order, List<LineItems> lineItems) {
        // Create and populate the response object
        FoodOrderPOJO foodOrderPOJO = new FoodOrderPOJO();
        foodOrderPOJO.setOrderId(order.getOrderId());
        foodOrderPOJO.setOrderStatus(order.getOrderStatus());
        foodOrderPOJO.setCustomerName(order.getCustomerName());
        // Set the line items directly
        foodOrderPOJO.setLineItems(lineItems);
        return foodOrderPOJO;
    }
}
