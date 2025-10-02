package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Address;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.CuisineType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OpeningHours;

import java.util.UUID;

public record CreateRestaurantCommand(UUID ownerId,
                                      Address address,
                                      String email,
                                      String pictureURL,
                                      Integer defaultPrepTime,
                                      CuisineType cuisineType,
                                      OpeningHours openingHours) {
    public CreateRestaurantCommand{
        //constraints, checks, etc.
    }
}
