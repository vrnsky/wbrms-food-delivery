package my.edu.sunway.wbrms.wbrmsfooddelivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import my.edu.sunway.wbrms.wbrmsfooddelivery.entity.DeliveryEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Schema(description = "Definition of order delivery")
public record Delivery(

        @Schema(description = "ID of delivery")
        UUID id,

        @Schema(description = "Creation time")
        LocalDateTime createdDateTime,

        @Schema(description = "Customer's name", example = "John Doe")
        @NotBlank(message = "Name of customer must not be blank")
        String customer,

        @Schema(description = "Price", example = "100.51")
        BigDecimal amount,

        @Schema(description = "Delivery status", example = "Created, Preparing, Delivering, Delivered")
        DeliveryStatus deliveryStatus,

        @Schema(description = "Delivery type")
        @NotNull(message = "Delivery type must be specified")
        DeliveryType deliveryType,

        @Schema(description = "Delivery Partner", allowableValues = {"grab", "foodpanda", "lalamove", "beepit"})
        @Pattern(regexp = "grab|foodpanda|lalamove|beepit", message = "Invalid delivery partner")
        @NotNull(message = "Delivery Partner must be specified")
        String partner,

        @Schema(description = "Expected delivery or pick up time")
        LocalDateTime deliveryTime,

        @Schema(description = "Cancellation time")
        LocalDateTime cancellationDateTime,

        @Schema(description = "Cancellation reason")
        String cancellationReason
) {

    public static Delivery fromDeliveryEntity(DeliveryEntity delivery) {
        return new Delivery(
                delivery.getId(),
                delivery.getCreatedDateTime(),
                delivery.getCustomer(),
                delivery.getAmount(),
                delivery.getDeliveryStatus(),
                delivery.getDeliveryType(),
                delivery.getPartner(),
                delivery.getDeliveryTime(),
                delivery.getCancellationDateTime(),
                delivery.getCancellationReason()
        );
    }

    public static List<Delivery> fromDeliveryEntities(List<DeliveryEntity> deliveryEntities) {
        return deliveryEntities.stream()
                .map(Delivery::fromDeliveryEntity)
                .collect(Collectors.toList());
    }

    public static Delivery canceledDelivery(Delivery delivery) {
        return new Delivery(
                delivery.id(),
                delivery.createdDateTime(),
                delivery.customer(),
                delivery.amount(),
                DeliveryStatus.Cancelled,
                delivery.deliveryType(),
                delivery.partner(),
                delivery.deliveryTime(),
                LocalDateTime.now(),
                "Initiated by restaurant" //TODO WBFDL-3
        );
    }
}
