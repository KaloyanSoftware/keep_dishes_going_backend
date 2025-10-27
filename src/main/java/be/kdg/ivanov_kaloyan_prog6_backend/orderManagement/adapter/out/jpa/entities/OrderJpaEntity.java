package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.CustomerInfoEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.OrderLineEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "order_management", name = "orders")
public class OrderJpaEntity {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "restaurant_id")
    private UUID restaurantId;

    @Column(name="customer_session_id",  nullable = false)
    private UUID customerSessionId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            schema = "order_management",
            name = "order_lines",
            joinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderLineEmbeddable> orderLines = new ArrayList<>();

    @Embedded
    private CustomerInfoEmbeddable customerInfo;

    @Column(name = "total", nullable = false, precision = 14, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Order.OrderStatus status;

    protected OrderJpaEntity() {
    }

    public OrderJpaEntity(UUID id, UUID restaurantId, UUID customerSessionId, List<OrderLineEmbeddable> orderLines,
                          CustomerInfoEmbeddable customerInfo, BigDecimal total) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.customerSessionId = customerSessionId;
        this.orderLines = orderLines;
        this.customerInfo = customerInfo;
        this.total = total;
    }

    public UUID getId() {
        return id;
    }

    public List<OrderLineEmbeddable> getOrderLines() {
        return orderLines;
    }

    public CustomerInfoEmbeddable getCustomerInfo() {
        return customerInfo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setOrderLines(List<OrderLineEmbeddable> orderLines) {
        this.orderLines = orderLines;
    }

    public void setCustomerInfo(CustomerInfoEmbeddable customerInfo) {
        this.customerInfo = customerInfo;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public UUID getCustomerSessionId() {
        return customerSessionId;
    }

    public void setCustomerSessionId(UUID customerSessionid) {
        this.customerSessionId = customerSessionid;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Order.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(Order.OrderStatus status) {
        this.status = status;
    }
}
