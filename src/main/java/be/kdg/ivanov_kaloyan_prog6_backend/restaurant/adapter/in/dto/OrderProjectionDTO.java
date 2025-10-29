package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.common.dto.DeliveryInfoDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import java.util.Map;
import java.util.UUID;

public record OrderProjectionDTO(UUID orderId, UUID restaurantId, Map<String, Integer> orderLines,
                                 DeliveryInfoDTO deliveryInfo,
                                 String status) {
    public static OrderProjectionDTO from(final OrderProjection projection) {

        final DeliveryInfoDTO deliveryInfo = new DeliveryInfoDTO(projection.getDeliveryInfo().street(),projection.getDeliveryInfo().number(),
                projection.getDeliveryInfo().postalCode(), projection.getDeliveryInfo().city());

        return new OrderProjectionDTO(projection.getOrderId(), projection.getRestaurantId(), projection.getOrderLines(),
                deliveryInfo, projection.getStatus());
    }
}
