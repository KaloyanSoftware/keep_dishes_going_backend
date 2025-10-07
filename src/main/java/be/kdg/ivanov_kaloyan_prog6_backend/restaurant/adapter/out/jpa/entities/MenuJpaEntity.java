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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<DishJpaEntity> dishes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RestaurantJpaEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantJpaEntity restaurant) {
        this.restaurant = restaurant;
    }

    public List<DishJpaEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishJpaEntity> dishes) {
        this.dishes = dishes;
    }
}
