package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public record RestaurantProjectionId(UUID id) {
    public static RestaurantProjectionId of(UUID id) {
        return new RestaurantProjectionId(id);
    }
}
