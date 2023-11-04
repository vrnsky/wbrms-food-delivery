package my.edu.sunway.wbrms.wbrmsfooddelivery.dto;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    Created,
    Preparing,
    ReadyForDelivery,
    Delivering,
    Received,
    Cancelled

}
