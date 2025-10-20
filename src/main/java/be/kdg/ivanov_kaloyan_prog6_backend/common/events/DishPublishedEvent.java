package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DishPublishedEvent(LocalDateTime eventPit, UUID dishId, UUID restaurantId,
                                 String stockStatus, String name, String type, List<String> tags, String description,
                                 BigDecimal price, String pictureURL) implements DomainEvent {

    public DishPublishedEvent(LocalDateTime eventPit, UUID dishId, UUID restaurantId,
                              String stockStatus, String name, String type, List<String> tags, String description,
                              BigDecimal price, String pictureURL) {
        this.eventPit = eventPit;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.stockStatus = stockStatus;
        this.name = name;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public DishPublishedEvent(UUID dishId, UUID restaurantId, String stockStatus, String name, String type, List<String> tags, String description,
                              BigDecimal price, String pictureURL) {
        this(LocalDateTime.now(), dishId, restaurantId, stockStatus,  name, type, tags, description, price, pictureURL);
    }

    @Override
    public LocalDateTime eventPit(){
        return eventPit;
    }
}
