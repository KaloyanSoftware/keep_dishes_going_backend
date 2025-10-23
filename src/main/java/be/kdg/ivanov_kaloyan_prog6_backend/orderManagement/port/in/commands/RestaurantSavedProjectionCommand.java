package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.LocationDTO;
import java.util.UUID;

public record RestaurantSavedProjectionCommand(UUID id, LocationDTO location, String email, String pictureURL,
                                               Integer defaultPrepTime, String cuisineType) {
}
