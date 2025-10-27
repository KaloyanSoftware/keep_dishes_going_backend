package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.common.eventData.DeliveryInfoDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DomainEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderAcceptedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderReadyForPickUpEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderRejectedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderProjection {
    private UUID orderId;

    private UUID restaurantId;

    private String status;

    private Map<String, Integer> orderLines;

    private List<DomainEvent> eventStore = new ArrayList<>();

    private List<DomainEvent> uncommittedEvents = new ArrayList<>();

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

    public void accept(Address pickupAddress){
        this.status = "ACCEPTED";
        this.uncommittedEvents.add(new OrderAcceptedEvent(orderId, restaurantId,
                DeliveryInfoDTO.from(pickupAddress.street(), pickupAddress.number().toString(),
                        pickupAddress.postalCode(), pickupAddress.city()),
                DeliveryInfoDTO.from(deliveryInfo.street(), deliveryInfo.number(),
                        deliveryInfo.postalCode(), deliveryInfo.city())));
    }

    public void reject(){
        this.status = "REJECTED";
        this.uncommittedEvents.add(new OrderRejectedEvent(orderId));
    }

    public void ready(){
        this.status = "READY";
        this.uncommittedEvents.add(new OrderReadyForPickUpEvent(restaurantId, orderId));
    }

    public List<DomainEvent> getUncommittedEvents() {
        return new ArrayList<>(uncommittedEvents);
    }

    public void commitEvents(){
        this.eventStore.addAll(uncommittedEvents);
        uncommittedEvents.clear();
    }
}
