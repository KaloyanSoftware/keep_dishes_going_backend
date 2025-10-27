package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;

import java.util.Map;
import java.util.UUID;

public record OrderProjectionDTO(UUID orderId, UUID restaurantId, Map<String, Integer> orderLines) {
    public static OrderProjectionDTO from(final OrderProjection projection) {
        return new OrderProjectionDTO(projection.getOrderId(), projection.getRestaurantId(), projection.getOrderLines());
    }
}
