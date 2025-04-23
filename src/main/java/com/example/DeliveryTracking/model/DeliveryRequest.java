package com.example.DeliveryTracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryRequest {
    private DeliveryStatus status;
    private Long orderId;
    private String address;
}
