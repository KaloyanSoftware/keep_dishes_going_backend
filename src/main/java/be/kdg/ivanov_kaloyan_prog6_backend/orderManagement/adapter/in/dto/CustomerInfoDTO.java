package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.CustomerInfo;

public record CustomerInfoDTO(String name, LocationDTO deliveryAddress, String email) {

    public static CustomerInfoDTO from(final CustomerInfo customerInfo) {
        return new CustomerInfoDTO(customerInfo.name(), LocationDTO.from(customerInfo.deliveryAddress()), customerInfo.email());
    }
}
