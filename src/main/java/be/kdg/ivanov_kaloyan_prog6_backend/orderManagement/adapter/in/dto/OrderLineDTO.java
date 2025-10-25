package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.OrderLine;

public record OrderLineDTO(String name, Integer quantity, Double pricePerUnit, Double total) {

    public static OrderLineDTO from(final OrderLine orderLine) {
        return new OrderLineDTO(orderLine.name(), orderLine.quantity(), orderLine.pricePerUnit().doubleValue(),
                orderLine.total().doubleValue());
    }
}
