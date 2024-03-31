package com.example.sona.Repository;

import com.example.sona.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
Order findByOrderId(String orderId);
}
