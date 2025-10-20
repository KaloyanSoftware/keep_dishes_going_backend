package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.LocationEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Cuisine;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "order_management", name = "restaurant_projection")
public class RestaurantProjectionJpaEntity {
    @Id
    @Column(name = "uuid")
    private UUID id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(name = "default_prep_min", nullable = false)
    private Integer defaultPrepMinutes;

    @Embedded
    private LocationEmbeddable location;

    @Enumerated(EnumType.STRING)
    @Column(name = "cuisine_type", nullable = false)
    private Cuisine cuisine;

    public RestaurantProjectionJpaEntity(UUID id, String email, String pictureUrl, Integer defaultPrepMinutes,
                                         Cuisine cuisine, LocationEmbeddable location) {
        this.id = id;
        this.email = email;
        this.pictureUrl = pictureUrl;
        this.defaultPrepMinutes = defaultPrepMinutes;
        this.cuisine = cuisine;
        this.location = location;
    }

    public RestaurantProjectionJpaEntity() {

    }
}
