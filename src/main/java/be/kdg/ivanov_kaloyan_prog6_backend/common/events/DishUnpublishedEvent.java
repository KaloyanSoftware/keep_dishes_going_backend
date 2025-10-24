package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record DishUnpublishedEvent(UUID id, EventCatalog eventCatalog,LocalDateTime eventPit, UUID dishId) implements DomainEvent{

    public DishUnpublishedEvent(UUID dishId) {
        this(UUID.randomUUID(), EventCatalog.DISH_UNPUBLISHED, LocalDateTime.now(), dishId);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
