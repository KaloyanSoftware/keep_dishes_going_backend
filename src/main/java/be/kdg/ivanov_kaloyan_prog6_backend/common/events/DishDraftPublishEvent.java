package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.util.UUID;

public record DishDraftPublishEvent(UUID dishId, UUID restaurantId) implements DomainEvent{
}
