package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.UUID;

public record RestaurantId(UUID id) {

    public static RestaurantId create(){
        return new RestaurantId(UUID.randomUUID());
    }
}
