package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.util.UUID;

public record DishUnpublishedEvent(UUID dishId, UUID restaurantId,
                                   boolean orderable) implements DomainEvent{
}
