package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.DeliveryInfoEmbeddable;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(schema = "restaurant", name = "order_projection")
public class OrderProjectionJpaEntity {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @ElementCollection
    @CollectionTable(
            name = "order_projection_lines",
            schema = "restaurant",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "uuid")
    )
    @MapKeyColumn(name = "dish_name")
    @Column(name = "quantity")
    private Map<String, Integer> orderLines = new HashMap<>();

    @Embedded
    private DeliveryInfoEmbeddable deliveryInfo;

    @Column(name = "status", nullable = false)
    private String status;

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

    public Map<String, Integer> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(Map<String, Integer> orderLines) {
        this.orderLines = orderLines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeliveryInfoEmbeddable getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfoEmbeddable deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }
}
