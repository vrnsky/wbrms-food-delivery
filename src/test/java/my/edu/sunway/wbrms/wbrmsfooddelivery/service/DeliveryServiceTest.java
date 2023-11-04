package my.edu.sunway.wbrms.wbrmsfooddelivery.service;

import my.edu.sunway.wbrms.wbrmsfooddelivery.DatabaseIntegrationTest;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.Delivery;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.DeliveryStatus;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.DeliveryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class DeliveryServiceTest extends DatabaseIntegrationTest {

    @Autowired
    private DeliveryService deliveryService;

    @Test
    @Tag("positive")
    @DisplayName("Test case: Creation of new delivery")
    void testThatDeliveryServiceSaveDeliveryInfoCorrect() {
        var delivery = new Delivery(
                null,
                LocalDateTime.now(),
                "Egor Voronianskii",
                BigDecimal.valueOf(150_00L),
                DeliveryStatus.ReadyForDelivery,
                DeliveryType.DELIVERY,
                "foodpanda",
                LocalDateTime.now(),
                null, null
        );

        var savedDelivery = deliveryService.createDelivery(delivery);
        Assertions.assertNotNull(savedDelivery);
        Assertions.assertNotNull(savedDelivery.id());
    }
}