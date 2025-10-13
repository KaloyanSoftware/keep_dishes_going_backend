package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.util.UUID;

public record DishBackInStockEvent(UUID dishId, UUID restaurantId,
                                   boolean published, boolean inStock) implements DomainEvent {
}
