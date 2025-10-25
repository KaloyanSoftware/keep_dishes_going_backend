package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;

import java.time.LocalDateTime;
import java.util.UUID;

public record DishBackInStockEvent(LocalDateTime eventPit, UUID dishId, String stockStatus) implements DomainEvent {

    public DishBackInStockEvent(UUID dishId){
        this(LocalDateTime.now(), dishId, Dish.StockStatus.IN_STOCK.name());
    }

    @Override
    public LocalDateTime eventPit(){
        return eventPit;
    }
}
