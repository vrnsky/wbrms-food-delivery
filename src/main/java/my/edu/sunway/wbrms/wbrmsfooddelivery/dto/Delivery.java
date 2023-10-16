package my.edu.sunway.wbrms.wbrmsfooddelivery.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Definition of order delivery")
public record Delivery(

        @Schema(description = "Creation time")
        LocalDateTime createdDateTime,

        @Schema(description = "Customer's name")
        String customer,

        @Schema(description = "Number of items")
        int items,

        @Schema(description = "Price")
        BigDecimal amount,

        @Schema(description = "Delivery status")
        String deliveryStatus
) {
}
