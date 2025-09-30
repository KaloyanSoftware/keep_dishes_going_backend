package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.CuisineType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "restaurantManagement", schema = "restaurant")
public class RestaurantJpaEntity {
    @Id
    private UUID id;

    @Column(name = "owner_id", nullable = false, unique = true)
    private UUID ownerId;

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

    public RestaurantJpaEntity(UUID id,
                               UUID ownerId,
                               String email,
                               String pictureUrl,
                               Integer defaultPrepMinutes,
                               CuisineType cuisineType,
                               AddressEmbeddable address) {
        this.id = id;
        this.ownerId = ownerId;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.defaultPrepMinutes = defaultPrepMinutes;
        this.cuisineType = cuisineType;
        this.address = address;
    }

    public RestaurantJpaEntity() {
    }
}
