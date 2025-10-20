package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record DishBackInStockEvent(LocalDateTime eventPit, UUID dishId, UUID restaurantId,
                                   boolean orderable) implements DomainEvent {

    public DishBackInStockEvent(LocalDateTime eventPit, UUID dishId, UUID restaurantId, boolean orderable) {
        this.eventPit = eventPit;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.orderable = orderable;
    }

    public DishBackInStockEvent(UUID dishId, UUID restaurantId, boolean orderable) {
        this(LocalDateTime.now(), dishId, restaurantId, orderable);
    }

    @Override
    public LocalDateTime eventPit(){
        return eventPit;
    }
}
