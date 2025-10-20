package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Address;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.CuisineType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OpeningHours;
import org.springframework.util.Assert;
import java.util.UUID;

public record CreateRestaurantCommand(UUID ownerId,
                                      Address address,
                                      String email,
                                      String pictureURL,
                                      Integer defaultPrepTime,
                                      CuisineType cuisineType,
                                      OpeningHours openingHours) {
    public CreateRestaurantCommand{
        Assert.notNull(ownerId, "ownerId must not be null");
        Assert.notNull(address, "address must not be null");
        Assert.notNull(email, "email must not be null");
        Assert.notNull(pictureURL, "pictureURL must not be null");
        Assert.notNull(defaultPrepTime, "defaultPrepTime must not be null");
        Assert.notNull(cuisineType, "cuisineType must not be null");
        Assert.notNull(openingHours, "openingHours must not be null");
    }
}
