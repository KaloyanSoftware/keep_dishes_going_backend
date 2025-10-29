package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.Map;
import java.util.UUID;

public class OrderProjection {
    private UUID orderId;

    private UUID restaurantId;

    private String status;

    private Map<String, Integer> orderLines;

    private DeliveryInfo deliveryInfo;

    public OrderProjection(UUID orderId, UUID restaurantId, Map<String, Integer> orderLines, DeliveryInfo deliveryInfo) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.orderLines = orderLines;
        this.status = "PENDING";
        this.deliveryInfo = deliveryInfo;
    }

    public static OrderProjection rehydrate(UUID orderId, UUID restaurantId,
                                            Map<String, Integer> orderLines, String status, DeliveryInfo deliveryInfo) {
        OrderProjection projection = new OrderProjection(orderId, restaurantId, orderLines, deliveryInfo);
        projection.status = status != null ? status : "PENDING";
        return projection;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public Map<String, Integer> getOrderLines() {
        return orderLines;
    }

    public String getStatus() {
        return status;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void changeStatus(String status){
        this.status = status;
    }
}
