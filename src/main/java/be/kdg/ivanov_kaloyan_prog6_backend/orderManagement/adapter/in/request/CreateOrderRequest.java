package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.CustomerInfoDTO;

public record CreateOrderRequest(CustomerInfoDTO customerInfo) {
}
