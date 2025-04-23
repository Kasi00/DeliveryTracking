package com.example.DeliveryTracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class DeliveryMessage {
    private Long deliveryId;
    private DeliveryStatus deliveryStatus;

}
