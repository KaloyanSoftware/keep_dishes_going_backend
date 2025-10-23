package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "restaurant", name = "menu_events")
public class MenuEventJpaEntity {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "event_pit", nullable = false)
    private LocalDateTime eventPit;

    @Column(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "stock_status", nullable = false)
    private String stockStatus;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "dish_type", nullable = false)
    private String dishType;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @ElementCollection
    @CollectionTable(
            name = "menu_event_tags",
            schema = "restaurant",
            joinColumns = @JoinColumn(name = "menu_event_id", referencedColumnName = "uuid")
    )
    @Column(name = "tag", nullable = false)
    private List<String> tags;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "picture_url", nullable = false)
    private String pictureURL;

    public MenuEventJpaEntity() {
    }

    public MenuEventJpaEntity(UUID id,
                              LocalDateTime eventPit,
                              UUID dishId,
                              UUID restaurantId,
                              String stockStatus,
                              String name,
                              String dishType,
                              String eventType,
                              List<String> tags,
                              String description,
                              BigDecimal price,
                              String pictureURL) {
        this(id, eventPit, eventType,dishId, restaurantId);
        this.stockStatus = stockStatus;
        this.name = name;
        this.dishType = dishType;
        this.eventType = eventType;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public MenuEventJpaEntity(UUID id, LocalDateTime eventPit, String eventType, UUID dishId, UUID restaurantId) {
        this.id = id;
        this.eventPit = eventPit;
        this.eventType = eventType;
        this.dishId = dishId;
        this.restaurantId = restaurantId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getEventPit() {
        return eventPit;
    }

    public void setEventPit(LocalDateTime eventPit) {
        this.eventPit = eventPit;
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
        this.dishId = dishId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String type) {
        this.eventType = type;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }
}
