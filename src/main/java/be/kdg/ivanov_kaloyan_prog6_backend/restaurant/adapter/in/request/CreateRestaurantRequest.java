package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.AddressDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.OpeningHoursDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.CuisineType;
import java.util.UUID;

public record CreateRestaurantRequest(
        String ownerId,
        AddressDTO address,
        String email,
        String pictureURL,
        Integer defaultPrepTime,
        CuisineType cuisineType,
        OpeningHoursDTO openingHours)
{
}
