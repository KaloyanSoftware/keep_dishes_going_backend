package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import be.kdg.ivanov_kaloyan_prog6_backend.common.dto.DeliveryCoordinatesDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.common.dto.DeliveryInfoDTO;
import org.springframework.modulith.events.Externalized;
import java.time.LocalDateTime;
import java.util.UUID;

@Externalized("kdg.events::#{'restaurant.' + #this.restaurantId() + '.order.accepted.v1'}")
public record OrderAcceptedEvent(UUID eventId, LocalDateTime occurredAt,
                                 DeliveryCoordinatesDTO pickupCoordinates,
                                 DeliveryCoordinatesDTO dropoffCoordinates, UUID restaurantId, UUID orderId,
                                 DeliveryInfoDTO pickupAddress,
                                 DeliveryInfoDTO dropoffAddress) implements DomainEvent {

    public OrderAcceptedEvent(UUID restaurantId, UUID orderId, DeliveryInfoDTO pickupAddress
            , DeliveryInfoDTO dropoffAddress) {
        this(UUID.randomUUID(), LocalDateTime.now(),
                //hardcoded coordinates for convenience
                new DeliveryCoordinatesDTO(51.1869, 4.3738),
                new DeliveryCoordinatesDTO(51.1873, 4.3748),
                restaurantId, orderId,pickupAddress , dropoffAddress);
    }

    @Override
    public LocalDateTime eventPit() {
        return occurredAt;
    }
}
