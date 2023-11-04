package my.edu.sunway.wbrms.wbrmsfooddelivery.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.Delivery;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.SearchRequest;
import my.edu.sunway.wbrms.wbrmsfooddelivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Operation(summary = "Creation of new delivery entity")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/create")
    public Delivery createDelivery(
            @Parameter(description = "Parameters of delivery") @RequestBody @Valid Delivery delivery) {
        return deliveryService.createDelivery(delivery);
    }

    @Operation(summary = "Update existing delivery")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{id}")
    public Delivery updateDelivery(
            @Parameter(name = "id", description = "ID of existing delivery") @PathVariable UUID id,
            @Parameter(description = "New definition of delivery") @RequestBody @Valid Delivery delivery
    ) {
        return deliveryService.updateDelivery(id, delivery);
    }

    @Operation(summary = "Cancel existing delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/cancel/{id}")
    public void cancelDelivery(
            @Parameter(name = "id", description = "ID of existing delivery") @PathVariable(name = "id") UUID id) {
        deliveryService.cancelDelivery(id);
    }

    @Operation(summary = "Searching deliveries by specified parameters")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/search")
    public List<Delivery> searchDelivery(
            @Parameter(description = "Search entity definition") @RequestBody SearchRequest searchRequest
    ) {
        return deliveryService.searchDelivery(searchRequest);
    }
}
