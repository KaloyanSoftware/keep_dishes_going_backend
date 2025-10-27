package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import be.kdg.ivanov_kaloyan_prog6_backend.common.eventData.DeliveryInfoDTO;
import org.springframework.modulith.events.Externalized;

import java.time.LocalDateTime;
import java.util.UUID;

@Externalized("kdg.events::#{'restaurant.' + #this.restaurantId() + '.order.accepted.v1'}")
public record OrderAcceptedEvent(UUID eventId, LocalDateTime occuredAt, UUID restaurantId, UUID orderId, DeliveryInfoDTO pickupCoordinates,
                                 DeliveryInfoDTO dropoffAddress) implements DomainEvent {

    public OrderAcceptedEvent(UUID restaurantId, UUID orderId, DeliveryInfoDTO pickupCoordinates, DeliveryInfoDTO dropoffAddress) {
        this(UUID.randomUUID(), LocalDateTime.now(), restaurantId, orderId, pickupCoordinates, dropoffAddress);
    }

    @Override
    public LocalDateTime eventPit() {
        return occuredAt;
    }
}
