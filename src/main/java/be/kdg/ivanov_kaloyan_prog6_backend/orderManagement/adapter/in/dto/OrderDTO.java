package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import java.util.List;

public record OrderDTO(String id, String restaurantId, List<OrderLineDTO> orderLines, CustomerInfoDTO customerInfo, Double total, String status) {
    public static OrderDTO from(final Order order) {
        return new OrderDTO(order.getId().orderId().toString(),
                order.getRestaurantId().toString(),
                order.getOrderLines().stream().map(OrderLineDTO::from).toList(),
                CustomerInfoDTO.from(order.getCustomerInfo()),
                order.getTotal().doubleValue(),order.getStatus().name());
    }
}
