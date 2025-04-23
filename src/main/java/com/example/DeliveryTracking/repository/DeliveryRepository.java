package com.example.DeliveryTracking.repository;

import com.example.DeliveryTracking.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
