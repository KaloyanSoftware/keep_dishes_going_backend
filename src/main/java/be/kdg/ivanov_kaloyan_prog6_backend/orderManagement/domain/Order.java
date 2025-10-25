package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private OrderId id;
    private List<OrderLine> orderLines = new ArrayList<>();
    private CustomerInfo customerInfo;
    private BigDecimal total;
    private UUID customerSessionId;

    public Order(Basket basket, CustomerInfo customerInfo, UUID customerSessionId) {
        this.id = OrderId.create();
        this.orderLines.addAll(toOrderLines(basket));
        this.customerInfo = customerInfo;
        this.customerSessionId = customerSessionId;
        calculateTotal();
    }

    public Order(OrderId id, List<OrderLine> orderLines, CustomerInfo customerInfo, BigDecimal total,
                 UUID customerSessionId) {
        this.id = id;
        this.orderLines = orderLines;
        this.customerInfo = customerInfo;
        this.total = total;
        this.customerSessionId = customerSessionId;
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
}
