package com.example.DeliveryTracking.service;

import com.example.DeliveryTracking.model.Delivery;
import com.example.DeliveryTracking.model.DeliveryMessage;
import com.example.DeliveryTracking.model.DeliveryRequest;
import com.example.DeliveryTracking.model.DeliveryStatus;
import com.example.DeliveryTracking.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final RabbitTemplate rabbitTemplate;
    private final DeliveryProducer deliveryProducer;

    public Delivery createDelivery(DeliveryRequest request) {
        Delivery delivery = new Delivery();
        delivery.setStatus(request.getStatus());
        delivery.setAddress(request.getAddress());
        delivery.setOrderId(request.getOrderId());

        Delivery savedDelivery = deliveryRepository.save(delivery);

        // Send to RabbitMQ
        deliveryProducer.sendDeliveryEvent(savedDelivery.getOrderId(), savedDelivery.getStatus());

        return savedDelivery;
    }

    public Delivery getDelivery(Long id) {
       return deliveryRepository.findById(id).orElseThrow(()-> new RuntimeException("No delivery with id " + id));

    }


    public List<Delivery> getDeliveries(){
        return deliveryRepository.findAll();
    }

    public void updateStatus(Long id, DeliveryStatus status) {
        Delivery delivery= deliveryRepository.findById(id).orElseThrow(()-> new RuntimeException("No delivery with id " + id));
        delivery.setStatus(status);
        Delivery updatedDelivery=deliveryRepository.save(delivery);
        deliveryProducer.sendDeliveryEvent(updatedDelivery.getOrderId(), updatedDelivery.getStatus());
    }











}
