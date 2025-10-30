package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

//@Externalized("kdg.events::#{'delivery.' + #this.restaurantId() + '.order.delivered.v1'}")
public record OrderDeliveredEvent(UUID eventId, LocalDateTime occurredAt, UUID restaurantId, UUID orderId) implements DomainEvent{


    @Override
    public LocalDateTime eventPit() {
        return occurredAt;
    }
}
