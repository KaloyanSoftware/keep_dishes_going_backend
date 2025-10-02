package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;

import java.util.UUID;

public record RestaurantDTO(UUID id, UUID ownerId, AddressDTO address, String email, String pictureURL,
                            Integer defaultPrepTime, CuisineType cuisineType, OpeningHoursDTO openingHoursDTO) {

    public static RestaurantDTO from(final Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId().id(), restaurant.getOwnerId().id(),
                AddressDTO.from(restaurant.getAddress()), restaurant.getEmail(), restaurant.getPictureURL(),
                restaurant.getDefaultPrepTime(), restaurant.getCuisineType(), OpeningHoursDTO.from(restaurant.getOpeningHours()));
    }
}
