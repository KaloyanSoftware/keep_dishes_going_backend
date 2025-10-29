package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.AddressEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.DayScheduleEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.CuisineType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DayOfWeek;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "restaurant", schema = "restaurant")
public class RestaurantJpaEntity {
    @Id
    @Column(name = "uuid")
    private UUID id;

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "default_prep_min", nullable = false)
    private Integer defaultPrepMinutes;

    @Column(name = "is_open")
    private boolean isOpen = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "cuisine_type", nullable = false)
    private CuisineType cuisineType;

    @Embedded
    private AddressEmbeddable address;

    @ElementCollection
    @CollectionTable(
            name = "opening_hours",
            schema = "restaurant",
            joinColumns = @JoinColumn(name = "restaurant_id", referencedColumnName = "uuid")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "day_of_week")
    private Map<DayOfWeek, DayScheduleEmbeddable> openingHours = new HashMap<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true
    )
    @JoinColumn(name = "restaurant_id", referencedColumnName = "uuid")
    private List<RestaurantEventJpaEntity> events = new ArrayList<>();

    public RestaurantJpaEntity() {
    }

    public RestaurantJpaEntity(UUID id, UUID ownerId, String email, String pictureUrl,
                               Integer defaultPrepMinutes, CuisineType cuisineType,
                               AddressEmbeddable address,
                               Map<DayOfWeek, DayScheduleEmbeddable> openingHours) {
        setId(id);
        setOwnerId(ownerId);
        setEmail(email);
        setPictureUrl(pictureUrl);
        setDefaultPrepMinutes(defaultPrepMinutes);
        setCuisineType(cuisineType);
        setAddress(address);
        setOpeningHours(openingHours);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID owner_id) {
        this.ownerId = owner_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getDefaultPrepMinutes() {
        return defaultPrepMinutes;
    }

    public void setDefaultPrepMinutes(Integer defaultPrepMinutes) {
        this.defaultPrepMinutes = defaultPrepMinutes;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public AddressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }

    public Map<DayOfWeek, DayScheduleEmbeddable> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Map<DayOfWeek, DayScheduleEmbeddable> openingHours) {
        this.openingHours = openingHours;
    }

    public List<RestaurantEventJpaEntity> getEvents() {
        return events;
    }

    public void setEvents(List<RestaurantEventJpaEntity> events) {
        this.events = events;
    }
}
