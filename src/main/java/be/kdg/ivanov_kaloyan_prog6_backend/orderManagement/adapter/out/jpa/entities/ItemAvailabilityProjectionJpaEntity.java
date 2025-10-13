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

    @Column(name = "published", nullable = false)
    private boolean published;

    @Column(name = "in_stock", nullable = false)
    private boolean inStock;

    protected ItemAvailabilityProjectionJpaEntity() { }

    public ItemAvailabilityProjectionJpaEntity(UUID dishId, UUID restaurantId,
                                               boolean published, boolean inStock) {
        this.dishId = dishId;
        this.restaurantId = restaurantId;
        this.published = published;
        this.inStock = inStock;
    }

    public UUID getDishId() { return dishId; }
    public UUID getRestaurantId() { return restaurantId; }
    public boolean isPublished() { return published; }
    public boolean isInStock() { return inStock; }

    public void setPublished(boolean published) { this.published = published; }
    public void setInStock(boolean outOfStock) { this.inStock = outOfStock; }
}
