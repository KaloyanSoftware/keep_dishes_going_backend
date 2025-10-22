package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;
import java.util.UUID;

public record RestaurantProjectionDTO(UUID restaurantId,
                                      LocationDTO location,
                                      String email,
                                      String pictureURL,
                                      Integer defaultPrepTime,
                                      String cuisine) {

    public static RestaurantProjectionDTO from(final RestaurantProjection restaurantProjection) {
        return new RestaurantProjectionDTO(
                restaurantProjection.getRestaurantId(),
                LocationDTO.from(restaurantProjection.getLocation()),
                restaurantProjection.getEmail(),
                restaurantProjection.getPictureURL(),
                restaurantProjection.getDefaultPrepTime(),
                restaurantProjection.getCuisine().toString()
        );
    }
}
