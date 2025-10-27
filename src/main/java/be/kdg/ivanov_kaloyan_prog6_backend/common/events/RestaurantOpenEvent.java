package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record RestaurantOpenEvent(UUID id, LocalDateTime eventPit, EventCatalog eventCatalog,
                                  UUID restaurantId, boolean isOpen) implements DomainEvent{
    public RestaurantOpenEvent(UUID restaurantId) {
        this(UUID.randomUUID(), LocalDateTime.now(), EventCatalog.RESTAURANT_CLOSED, restaurantId, true);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
