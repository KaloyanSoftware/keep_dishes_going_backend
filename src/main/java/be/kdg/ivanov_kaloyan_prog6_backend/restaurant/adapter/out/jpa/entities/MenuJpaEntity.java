package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "restaurant", name = "menu")
public class MenuJpaEntity {
    @Id
    @Column(name = "uuid")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "uuid", nullable = false)
    private RestaurantJpaEntity restaurant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<DishJpaEntity> dishes;
}
