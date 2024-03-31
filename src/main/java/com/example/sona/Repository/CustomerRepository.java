package com.example.sona.Repository;

import com.example.sona.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
   Customer findByCustomerId(String customerId);

   Customer findByCustomerName(String customerName);
}
