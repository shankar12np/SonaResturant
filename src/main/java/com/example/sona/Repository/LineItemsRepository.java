package com.example.sona.Repository;

import com.example.sona.Entity.LineItems;
import com.example.sona.Model.LineItemsPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemsRepository  extends JpaRepository<LineItems, Long> {
    List<LineItemsPOJO> findByOrderId(String orderId);
    LineItems findLineItemsById(Long id);

    LineItems findByItemTitle(String itemTitle);
}
