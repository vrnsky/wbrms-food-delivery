package my.edu.sunway.wbrms.wbrmsfooddelivery.repository;

import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.DeliveryStatus;
import my.edu.sunway.wbrms.wbrmsfooddelivery.entity.DeliveryEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DeliveryEntitySpecification {
    private DeliveryEntitySpecification() {
    }

    public static Specification<DeliveryEntity> hasCreatedBetween(LocalDateTime afterCreationTime, LocalDateTime beforeCreationTime) {
        return (root, query, cb) -> {
            if (afterCreationTime == null && beforeCreationTime == null) {
                return null;
            } else if (afterCreationTime == null) {
                return cb.lessThanOrEqualTo(root.get("createdDateTime"), beforeCreationTime);
            } else if (beforeCreationTime == null) {
                return cb.lessThanOrEqualTo(root.get("createdDateTime"), afterCreationTime);
            } else {
                return cb.between(root.get("createdDateTime"), afterCreationTime, beforeCreationTime);
            }
        };
    }

    public static Specification<DeliveryEntity> hasCustomer(String customer) {
        return (root, query, cb) -> customer == null ? null : cb.equal(root.get("customer"), customer);
    }

    public static Specification<DeliveryEntity> hasAmount(BigDecimal amount) {
        return (root, query, cb) -> amount == null ? null : cb.equal(root.get("amount"), amount);
    }

    public static Specification<DeliveryEntity> hasDeliveryStatus(DeliveryStatus deliveryStatus) {
        return (root, query, cb) -> deliveryStatus == null ? null : cb.equal(root.get("deliveryStatus"), deliveryStatus);
    }

    public static Specification<DeliveryEntity> hasPartner(String partner) {
        return (root, query, cb) -> partner == null ? null : cb.equal(root.get("partner"), partner);
    }


    public static Specification<DeliveryEntity> hasDeliveredBetween(LocalDateTime afterDeliveryTime, LocalDateTime beforeDeliveryTime) {
        return (root, query, cb) -> {
            if (afterDeliveryTime == null && beforeDeliveryTime == null) {
                return null;
            } else if (afterDeliveryTime == null) {
                return cb.lessThanOrEqualTo(root.get("deliveryTime"), beforeDeliveryTime);
            } else if (beforeDeliveryTime == null) {
                return cb.lessThanOrEqualTo(root.get("deliveryTime"), afterDeliveryTime);
            } else {
                return cb.between(root.get("deliveryTime"), afterDeliveryTime, beforeDeliveryTime);
            }
        };
    }

    public static Specification<DeliveryEntity> hasCancellationDateTime(LocalDateTime afterCancellationTime, LocalDateTime beforeCancellationTime) {
        return (root, query, cb) -> {
            if (afterCancellationTime == null && beforeCancellationTime == null) {
                return null;
            } else if (afterCancellationTime == null) {
                return cb.lessThanOrEqualTo(root.get("cancellationDateTime"), beforeCancellationTime);
            } else if (beforeCancellationTime == null) {
                return cb.lessThanOrEqualTo(root.get("cancellationDateTime"), afterCancellationTime);
            } else {
                return cb.between(root.get("cancellationDateTime"), afterCancellationTime, beforeCancellationTime);
            }
        };
    }

    public static Specification<DeliveryEntity> hasCancellationReason(String cancellationReason) {
        return (root, query, cb) -> cancellationReason == null ? null : cb.equal(root.get("cancellationReason"), cancellationReason);
    }
}
