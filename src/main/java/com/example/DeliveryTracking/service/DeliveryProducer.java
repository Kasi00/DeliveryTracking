package com.example.DeliveryTracking.service;

import com.example.DeliveryTracking.model.Delivery;
import com.example.DeliveryTracking.model.DeliveryMessage;
import com.example.DeliveryTracking.model.DeliveryRequest;
import com.example.DeliveryTracking.model.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryProducer {
    private final RabbitTemplate rabbitTemplate;



    public void sendDeliveryEvent(Long deliveryId, DeliveryStatus status) {
        DeliveryMessage message = new DeliveryMessage(deliveryId, status);
        rabbitTemplate.convertAndSend("delivery.Exchange", "delivery.created", message);
    }


}
