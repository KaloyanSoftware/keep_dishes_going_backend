package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.AddressEmbeddable;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema="restaurant", name="restaurant_events")
public class RestaurantEventJpaEntity {

    @Id
    @Column(name = "uuid")
    private UUID id;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(nullable = false)
    private LocalDateTime eventPit;

    @Column(nullable = false, length = 50)
    private String eventType;

    @Embedded
    private AddressEmbeddable address;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="pictureURL", nullable = false)
    private String pictureURL;

    @Column(name = "default_prep_time", nullable = false)
    private Integer defaultPrepTime;

    @Column(name = "cuisine_type", nullable = false)
    private String cuisineType;

    public RestaurantEventJpaEntity() {}

    public RestaurantEventJpaEntity(UUID id, UUID restaurantId, LocalDateTime eventPit, String eventType, AddressEmbeddable address,
                                    String email, String pictureURL,
                                    Integer defaultPrepTime, String cuisineType) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.eventPit = eventPit;
        this.eventType = eventType;
        this.address = address;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID uuid) {
        this.id = uuid;
    }

    public LocalDateTime getEventPit() {
        return eventPit;
    }

    public void setEventPit(LocalDateTime eventPit) {
        this.eventPit = eventPit;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public AddressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Integer getDefaultPrepTime() {
        return defaultPrepTime;
    }

    public void setDefaultPrepTime(Integer defaultPrepTime) {
        this.defaultPrepTime = defaultPrepTime;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
}