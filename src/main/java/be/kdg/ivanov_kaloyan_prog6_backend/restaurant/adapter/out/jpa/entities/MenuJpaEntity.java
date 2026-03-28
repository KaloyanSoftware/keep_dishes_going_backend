package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "restaurant", name = "menu")
public class MenuJpaEntity {

    @Id
    @Column(name = "uuid")
    private UUID id;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;


    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishJpaEntity> dishes = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "menu_id", referencedColumnName = "uuid")
    private List<MenuEventJpaEntity> events;

    public MenuJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<DishJpaEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishJpaEntity> dishes) {
        this.dishes.clear();

        if (dishes == null) {
            return;
        }

        for (DishJpaEntity dish : dishes) {
            addDish(dish);
        }
    }

    public void addDish(DishJpaEntity dish) {
        if (dish == null) {
            return;
        }

        this.dishes.add(dish);
        dish.setMenu(this);
    }

    public void removeDish(DishJpaEntity dish) {
        if (dish == null) {
            return;
        }

        this.dishes.remove(dish);
        dish.setMenu(null);
    }

    public List<MenuEventJpaEntity> getEvents() {
        return events;
    }

    public void setEvents(List<MenuEventJpaEntity> events) {
        this.events = events;
    }
}
