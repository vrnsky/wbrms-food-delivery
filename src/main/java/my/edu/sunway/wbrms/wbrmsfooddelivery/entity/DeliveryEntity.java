package my.edu.sunway.wbrms.wbrmsfooddelivery.entity;

import jakarta.persistence.*;
import lombok.Data;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.Delivery;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.DeliveryStatus;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.DeliveryType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "DELIVERABLES")
public class DeliveryEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "CREATED_DATE_TIME")
    private LocalDateTime createdDateTime;

    @Column(name = "CUSTOMER")
    private String customer;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DELIVERY_STATUS")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Column(name = "DELIVERY_TYPE")
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;

    @Column(name = "PARTNER")
    private String partner;

    @Column(name = "DELIVERY_TIME")
    LocalDateTime deliveryTime;

    @Column(name = "CANCELLATION_DATE_TIME")
    private LocalDateTime cancellationDateTime;

    @Column(name = "CANCELLATION_REASON")
    private String cancellationReason;


    public static DeliveryEntity fromDelivery(Delivery delivery) {
        var deliveryEntity = new DeliveryEntity();
        deliveryEntity.setId(delivery.id());
        deliveryEntity.setCreatedDateTime(delivery.createdDateTime() == null ? LocalDateTime.now() : delivery.createdDateTime());
        deliveryEntity.setCustomer(delivery.customer());
        deliveryEntity.setAmount(delivery.amount());
        deliveryEntity.setDeliveryStatus(delivery.deliveryStatus() == null ? DeliveryStatus.Created : delivery.deliveryStatus());
        deliveryEntity.setDeliveryType(delivery.deliveryType());
        deliveryEntity.setPartner(delivery.partner());
        deliveryEntity.setDeliveryTime(delivery.deliveryTime());
        deliveryEntity.setCancellationDateTime(delivery.cancellationDateTime());
        deliveryEntity.setCancellationReason(delivery.cancellationReason());
        return deliveryEntity;
    }
}
