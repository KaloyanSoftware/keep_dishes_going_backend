package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record DishUnpublishedEvent(LocalDateTime eventPit, UUID dishId, UUID restaurantId,
                                   boolean orderable) implements DomainEvent{

    public DishUnpublishedEvent(LocalDateTime eventPit, UUID dishId, UUID restaurantId, boolean orderable) {
        this.eventPit = eventPit;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.orderable = orderable;
    }

    public DishUnpublishedEvent(UUID dishId, UUID restaurantId, boolean orderable) {
        this(LocalDateTime.now(), dishId, restaurantId, orderable);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
