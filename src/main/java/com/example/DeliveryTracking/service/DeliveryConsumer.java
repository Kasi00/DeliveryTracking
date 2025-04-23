package com.example.DeliveryTracking.service;

import com.example.DeliveryTracking.model.DeliveryMessage;
import com.example.DeliveryTracking.model.DeliveryRequest;
import com.example.DeliveryTracking.model.DeliveryStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryConsumer {
    private final DeliveryService deliveryService;


    @RabbitListener(queues = "delivery.queue")
    public void receiveDeliveryEvent(DeliveryMessage message) {
        System.out.println("Received delivery ID: " + message.getDeliveryId());
        System.out.println("Status: " + message.getDeliveryStatus());


    }

}
