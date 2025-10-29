package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import org.springframework.modulith.events.Externalized;
import java.time.LocalDateTime;
import java.util.UUID;

@Externalized("kdg.events::#{'restaurant.' + #this.restaurantId() + '.order.ready.v1'}")
public record OrderReadyForPickUpEvent(UUID eventId, LocalDateTime occurredAt, EventCatalog eventCatalog, UUID restaurantId, UUID orderId) implements DomainEvent{
    public OrderReadyForPickUpEvent(UUID restaurantId, UUID orderId) {
        this(UUID.randomUUID(), LocalDateTime.now(), EventCatalog.ORDER_READY, restaurantId, orderId);
    }

    @Override
    public LocalDateTime eventPit() {
        return occurredAt;
    }
}
