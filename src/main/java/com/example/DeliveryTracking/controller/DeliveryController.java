package com.example.DeliveryTracking.controller;

import com.example.DeliveryTracking.model.Delivery;
import com.example.DeliveryTracking.model.DeliveryMessage;
import com.example.DeliveryTracking.model.DeliveryRequest;
import com.example.DeliveryTracking.model.DeliveryStatus;
import com.example.DeliveryTracking.repository.DeliveryRepository;
import com.example.DeliveryTracking.service.DeliveryProducer;
import com.example.DeliveryTracking.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryProducer deliveryProducer;


    @GetMapping("/all")
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        List<Delivery> deliveries = deliveryService.getDeliveries();
        return new ResponseEntity<>(deliveries, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDelivery(@PathVariable long id) {
        Delivery delivery = deliveryService.getDelivery(id);
        return ResponseEntity.ok(delivery);

    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody DeliveryRequest deliveryRequest) {
        Delivery delivery = deliveryService.createDelivery(deliveryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(delivery);

    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Delivery> updateDeliveryStatus(@PathVariable Long id, @RequestBody DeliveryStatus status) {
        deliveryService.updateStatus(id, status);

        // Step 1: Send a message to RabbitMQ about the status update
        Delivery updatedDelivery = deliveryService.getDelivery(id);

        // Step 2: Return updated delivery
        return ResponseEntity.ok(updatedDelivery);


    }







}
