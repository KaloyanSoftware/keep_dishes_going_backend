package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import java.time.LocalDateTime;
import java.util.UUID;

public record DishOutOfStockEvent(UUID id, LocalDateTime eventPit, UUID dishId, EventCatalog eventCatalog, String stockStatus) implements DomainEvent {

    public DishOutOfStockEvent(UUID dishId) {
        this(UUID.randomUUID(), LocalDateTime.now(), dishId, EventCatalog.DISH_OUT_OF_STOCK ,Dish.StockStatus.OUT_OF_STOCK.name());
    }

    @Override
    public LocalDateTime eventPit(){
        return eventPit;
    }
}
