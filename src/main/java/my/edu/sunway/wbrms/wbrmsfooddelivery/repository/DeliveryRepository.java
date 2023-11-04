package my.edu.sunway.wbrms.wbrmsfooddelivery.repository;

import my.edu.sunway.wbrms.wbrmsfooddelivery.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, UUID> {
}
