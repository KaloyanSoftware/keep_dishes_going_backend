package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DishPublishedEvent(UUID id, LocalDateTime eventPit, EventCatalog eventCatalog, UUID dishId, UUID restaurantId,
                                 String stockStatus, String name, String type,List<String> tags, String description,
                                 BigDecimal price, String pictureURL) implements DomainEvent {

    public DishPublishedEvent(UUID dishId, UUID restaurantId, String stockStatus, String name, String type,List<String> tags, String description,
                              BigDecimal price, String pictureURL) {
        this(UUID.randomUUID(), LocalDateTime.now(), EventCatalog.DISH_PUBLISHED,dishId, restaurantId, stockStatus,  name, type ,tags, description, price, pictureURL);
    }

    @Override
    public LocalDateTime eventPit(){
        return eventPit;
    }
}
