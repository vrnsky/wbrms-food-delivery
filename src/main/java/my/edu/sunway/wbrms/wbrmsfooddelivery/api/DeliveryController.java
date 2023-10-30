package my.edu.sunway.wbrms.wbrmsfooddelivery.api;

import lombok.RequiredArgsConstructor;
import my.edu.sunway.wbrms.wbrmsfooddelivery.dto.Delivery;
import my.edu.sunway.wbrms.wbrmsfooddelivery.service.DeliveryService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/list")
    public Mono<List<Delivery>> list() {
        return Mono.just(deliveryService.getDefaultDeliveries());
    }

    @PostMapping("/create")
    public Mono<Delivery> create(@RequestBody Delivery delivery) {
        return Mono.empty();
    }

    @PutMapping("/update/{deliveryId}")
    public Mono<Delivery> update(@PathVariable String deliveryId, @RequestBody Delivery delivery) {
        return Mono.empty();
    }

    @DeleteMapping("/delete/{deliveryId}")
    public Mono<?> delete(@PathVariable String deliveryId) {
        return Mono.empty();
    }

}
