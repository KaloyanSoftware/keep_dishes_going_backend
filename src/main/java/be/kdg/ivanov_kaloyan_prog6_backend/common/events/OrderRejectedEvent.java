package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderRejectedEvent(UUID id, LocalDateTime eventPit, EventCatalog eventCatalog, UUID orderId) implements DomainEvent {

    public OrderRejectedEvent(UUID orderId) {
        this(UUID.randomUUID(), LocalDateTime.now(), EventCatalog.ORDER_REJECTED, orderId);
    }

    @Override
    public LocalDateTime eventPit(){
        return eventPit;
    }
}
