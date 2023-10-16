package my.edu.sunway.wbrms.wbrmsfooddelivery.service;

import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.Delivery;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.DeliveryStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    public List<Delivery> getDefaultDeliveries() {
        var deliveries = new ArrayList<Delivery>();
        for (var index = 0; index < 20; index++) {
            var deliveryStatus = index < 5 ? DeliveryStatus.Preparing : DeliveryStatus.ReadyForDelivery;
            var delivery = new Delivery(
                    LocalDateTime.now().minusDays(index).plusMinutes(index),
                    "Customer Name",
                    (index + 1) * 3 / 2,
                    BigDecimal.valueOf((index + 1) * 10L),
                    deliveryStatus.getDescription()
            );
            deliveries.add(delivery);
        }

        return deliveries;
    }
}
