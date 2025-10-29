package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.AddressEmbeddable;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "restaurant", name = "restaurant_events")
public class RestaurantEventJpaEntity {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "event_pit", nullable = false)
    private LocalDateTime eventPit;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Embedded
    private AddressEmbeddable address;

    @Column(name = "email")
    private String email;

    @Column(name = "pictureURL")
    private String pictureURL;

    @Column(name = "default_prep_time")
    private Integer defaultPrepTime;

    @Column(name = "cuisine_type")
    private String cuisineType;

    public RestaurantEventJpaEntity() {
        this.id = UUID.randomUUID();
    }

    public RestaurantEventJpaEntity(
            UUID id,
            UUID restaurantId,
            UUID orderId,
            LocalDateTime eventPit,
            String eventType,
            AddressEmbeddable address,
            String email,
            String pictureURL,
            Integer defaultPrepTime,
            String cuisineType
    ) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.orderId = orderId;
        this.eventPit = eventPit;
        this.eventType = eventType;
        this.address = address;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getRestaurantId() { return restaurantId; }
    public void setRestaurantId(UUID restaurantId) { this.restaurantId = restaurantId; }

    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }

    public LocalDateTime getEventPit() { return eventPit; }
    public void setEventPit(LocalDateTime eventPit) { this.eventPit = eventPit; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }

    public AddressEmbeddable getAddress() { return address; }
    public void setAddress(AddressEmbeddable address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPictureURL() { return pictureURL; }
    public void setPictureURL(String pictureURL) { this.pictureURL = pictureURL; }

    public Integer getDefaultPrepTime() { return defaultPrepTime; }
    public void setDefaultPrepTime(Integer defaultPrepTime) { this.defaultPrepTime = defaultPrepTime; }

    public String getCuisineType() { return cuisineType; }
    public void setCuisineType(String cuisineType) { this.cuisineType = cuisineType; }
}

