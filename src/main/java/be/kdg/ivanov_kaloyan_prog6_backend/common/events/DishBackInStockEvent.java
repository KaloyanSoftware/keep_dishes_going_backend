package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import java.time.LocalDateTime;
import java.util.UUID;

public record DishBackInStockEvent(UUID id, LocalDateTime eventPit, UUID dishId, EventCatalog eventCatalog,String stockStatus) implements DomainEvent {

    public DishBackInStockEvent(UUID dishId){
        this(UUID.randomUUID(), LocalDateTime.now(), dishId, EventCatalog.DISH_BACK_IN_STOCK, Dish.StockStatus.IN_STOCK.name());
    }

    @Override
    public LocalDateTime eventPit(){
        return eventPit;
    }
}
