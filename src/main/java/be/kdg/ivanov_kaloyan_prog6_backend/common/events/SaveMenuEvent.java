package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record SaveMenuEvent(LocalDateTime eventPit, UUID id, UUID restaurantId) implements DomainEvent{

    public SaveMenuEvent(LocalDateTime eventPit, UUID id, UUID restaurantId) {
        this.eventPit = eventPit;
        this.id = id;
        this.restaurantId = restaurantId;
    }

    public SaveMenuEvent(UUID id, UUID restaurantId) {
        this(LocalDateTime.now(), id, restaurantId);
    }

    @Override
    public LocalDateTime eventPit(){
        return eventPit;
    }
}
