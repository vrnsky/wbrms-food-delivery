package my.edu.sunway.wbrms.wbrmsfooddelivery.dto;

import lombok.Getter;

@Getter
public enum DeliveryStatus {

    ReadyForDelivery("Ready for Delivery"),
    Preparing("Preparing"),
    Received("Received");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }
}
