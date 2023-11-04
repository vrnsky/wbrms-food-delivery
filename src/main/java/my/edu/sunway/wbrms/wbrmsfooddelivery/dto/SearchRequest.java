package my.edu.sunway.wbrms.wbrmsfooddelivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Definition of search request")
public record SearchRequest(

        @Schema(description = "Return all deliverables which were created after specified date")
        LocalDateTime afterCreationDateTime,

        @Schema(description = "Return all deliverables which were create before specified date")
        LocalDateTime beforeCreationDateTime,

        @Schema(description = "Name of customer who ordered delivery")
        String customer,

        @Schema(description = "Amount of delivery", example = "100.50")
        BigDecimal amount,

        @Schema(description = "Delivery status")
        DeliveryStatus deliveryStatus,

        @Schema(description = "Delivery partner")
        @Pattern(regexp = "grab|foodpanda|lalamove|beepit")
        String deliveryPartner,

        @Schema(description = "Return all deliverables which were delivered after specified date")
        LocalDateTime afterDeliveredDateTime,

        @Schema(description = "Return all deliverables which were delivered before specified date")
        LocalDateTime beforeDeliveredDateTime,

        @Schema(description = "Return all deliverables which were cancelled after specified date")
        LocalDateTime afterCancellationDateTime,

        @Schema(description = "Return all deliverables which were cancelled before specified date")
        LocalDateTime beforeCancellationDateTime

) {
}
