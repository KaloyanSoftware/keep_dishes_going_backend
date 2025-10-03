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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", referencedColumnName = "uuid", nullable = false, unique = true)
    private OwnerJpaEntity ownerJpa;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "default_prep_min", nullable = false)
    private Integer defaultPrepMinutes;

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

    public RestaurantJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public AddressEmbeddable getAddress() {
        return address;
    }

    public Map<DayOfWeek, DayScheduleEmbeddable> getOpeningHours() {
        return openingHours;
    }

    public OwnerJpaEntity getOwner() {
        return ownerJpa;
    }

    public void setOwner(OwnerJpaEntity owner) {
        this.ownerJpa = owner;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setDefaultPrepMinutes(Integer defaultPrepMinutes) {
        this.defaultPrepMinutes = defaultPrepMinutes;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }

    public void setOpeningHours(Map<DayOfWeek, DayScheduleEmbeddable> openingHours) {
        this.openingHours = openingHours;
    }

    public String getEmail() {
        return email;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Integer getDefaultPrepMinutes() {
        return defaultPrepMinutes;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }
}
