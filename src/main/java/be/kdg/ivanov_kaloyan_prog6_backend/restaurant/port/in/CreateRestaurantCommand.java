package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.AddressDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.OpeningHoursDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.CuisineType;
import java.util.UUID;

public record CreateRestaurantCommand(UUID ownerId,
                                      AddressDTO addressDTO,
                                      String email,
                                      String pictureURL,
                                      Double defaultPrepTime,
                                      CuisineType cuisineType,
                                      OpeningHoursDTO openingHoursDTO) {
    public CreateRestaurantCommand{
        //constraints, checks, etc.
    }
}
