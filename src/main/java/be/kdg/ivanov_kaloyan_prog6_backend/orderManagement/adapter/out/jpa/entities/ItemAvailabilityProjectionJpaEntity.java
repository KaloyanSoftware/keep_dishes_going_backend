package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "item_availability", schema = "order_management")
public class ItemAvailabilityProjectionJpaEntity {

    @Id
    @Column(name = "dish_id", nullable = false)
    private UUID dishId;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "orderable", nullable = false)
    private boolean orderable;

    protected ItemAvailabilityProjectionJpaEntity() { }

    public ItemAvailabilityProjectionJpaEntity(UUID dishId, UUID restaurantId,boolean orderable) {
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.orderable = orderable;
    }

    public UUID getDishId() { return dishId; }
    public UUID getRestaurantId() { return restaurantId; }
    public boolean isOrderable() { return orderable; }

    public void setOrderable(boolean published) { this.orderable = published; }
}
