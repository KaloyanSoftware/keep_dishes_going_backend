package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DomainEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderPlacedEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {
    private OrderId id;

    private UUID restaurantId;

    private List<OrderLine> orderLines = new ArrayList<>();

    private List<DomainEvent> eventStore = new ArrayList<>();

    private List<DomainEvent> uncommittedEvents = new ArrayList<>();

    private CustomerInfo customerInfo;

    private BigDecimal total;

    private UUID customerSessionId;

    private OrderStatus status;

    public enum OrderStatus{
        PENDING, ACCEPTED, REJECTED, READY, PICKED_UP, DELIVERED
    }

    public Order(Basket basket, CustomerInfo customerInfo, UUID customerSessionId) {
        this.id = OrderId.create();
        this.orderLines.addAll(toOrderLines(basket));
        this.customerInfo = customerInfo;
        this.customerSessionId = customerSessionId;
        this.restaurantId = basket.getRestaurantId();
        this.status = OrderStatus.PENDING;
        calculateTotal();
    }

    public static Order create(Basket basket, CustomerInfo customerInfo, UUID customerSessionId){
        final Order order = new Order(basket, customerInfo, customerSessionId);
        final Map<String, Integer> eventOrderLines = basket.getBasketLines()
                .values()
                .stream()
                .collect(Collectors.toMap(
                        BasketLine::getName,
                        BasketLine::getQuantity
                ));

        final Location deliveryInfo = order.getCustomerInfo().deliveryAddress();

        order.uncommittedEvents.add(new OrderPlacedEvent(order.id.orderId(), order.restaurantId, eventOrderLines,
                deliveryInfo.number().toString(), deliveryInfo.street(), deliveryInfo.postalCode(), deliveryInfo.city()));
        return order;
    }

    public Order(OrderId id, UUID restaurantId, List<OrderLine> orderLines, CustomerInfo customerInfo, BigDecimal total,
                 UUID customerSessionId, OrderStatus status) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.orderLines = orderLines;
        this.customerInfo = customerInfo;
        this.total = total;
        this.customerSessionId = customerSessionId;
        this.status = status;
    }

    private List<OrderLine> toOrderLines(Basket basket) {
        return basket.getBasketLines()
                .values()
                .stream()
                .map(basketLine -> new OrderLine(
                        basketLine.getName(),
                        basketLine.getQuantity(),
                        basketLine.getPrice()))
                .toList();
    }

    private void calculateTotal(){
        double total = 0.0;

        for (OrderLine orderLine : orderLines){
           total+=orderLine.total().doubleValue();
        }

        this.total = BigDecimal.valueOf(total);
    }

    public OrderId getId() {
        return id;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public UUID getCustomerSessionId() {
        return customerSessionId;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public List<DomainEvent> getUncommittedEvents() {
        return uncommittedEvents;
    }

    public void accepted(OrderStatus status){
        this.status = status;
    }

    public void rejected(OrderStatus status){
        this.status = status;
    }

    public void ready(OrderStatus status){
        this.status = status;
    }

    public void pickedUp(OrderStatus status){
        this.status = status;
    }

    public void delivered(OrderStatus status){
        this.status = status;
    }

    public void commitEvents(){
        this.eventStore.addAll(uncommittedEvents);
        uncommittedEvents.clear();
    }

    public OrderStatus getStatus() {
        return status;
    }
}
