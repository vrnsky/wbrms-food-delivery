package my.edu.sunway.wbrms.wbrmsfooddelivery.service;

import lombok.RequiredArgsConstructor;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.Delivery;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.SearchRequest;
import my.edu.sunway.wbrms.wbrmsfooddelivery.entity.DeliveryEntity;
import my.edu.sunway.wbrms.wbrmsfooddelivery.exception.NotFoundDeliveryException;
import my.edu.sunway.wbrms.wbrmsfooddelivery.repository.DeliveryRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static my.edu.sunway.wbrms.wbrmsfooddelivery.repository.DeliveryEntitySpecification.*;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public Delivery createDelivery(Delivery delivery) {
        var savedDelivery = deliveryRepository.save(DeliveryEntity.fromDelivery(delivery));
        return Delivery.fromDeliveryEntity(savedDelivery);
    }

    public Delivery updateDelivery(UUID id, Delivery delivery) {
        var existingDelivery = deliveryRepository.findById(id);
        if (existingDelivery.isEmpty()) {
            throw new NotFoundDeliveryException("Delivery with ID = " + id + " not found");
        }
        var updateDeliveryEntity = deliveryRepository.save(DeliveryEntity.fromDelivery(delivery));
        return Delivery.fromDeliveryEntity(updateDeliveryEntity);
    }

    public void cancelDelivery(UUID id) {
        var existingDelivery = deliveryRepository.findById(id);
        if (existingDelivery.isEmpty()) {
            throw new NotFoundDeliveryException("Delivery with ID = " + id + " not found");
        }
        var cancelledDelivery = Delivery.canceledDelivery(Delivery.fromDeliveryEntity(existingDelivery.get()));
        deliveryRepository.save(DeliveryEntity.fromDelivery(cancelledDelivery));
    }

    public List<Delivery> searchDelivery(SearchRequest searchRequest) {
        final var deliveries = deliveryRepository.findAll(
                Specification.where(hasCustomer(searchRequest.customer()))
                        .and(hasCreatedBetween(searchRequest.afterCreationDateTime(), searchRequest.beforeCreationDateTime()))
                        .and(hasPartner(searchRequest.deliveryPartner()))
                        .and(hasAmount(searchRequest.amount()))
                        .and(hasDeliveredBetween(searchRequest.afterDeliveredDateTime(), searchRequest.beforeDeliveredDateTime()))
        );

        return Delivery.fromDeliveryEntities(deliveries);
    }
}
