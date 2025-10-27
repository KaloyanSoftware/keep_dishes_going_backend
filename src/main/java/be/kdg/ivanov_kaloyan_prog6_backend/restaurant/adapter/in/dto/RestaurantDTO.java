package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;

public record RestaurantDTO(String id, String ownerId, AddressDTO address, String email, String pictureURL,
                            Integer defaultPrepTime, CuisineType cuisineType, OpeningHoursDTO openingHoursDTO, boolean isOpen) {

    public static RestaurantDTO from(final Restaurant restaurant) {
        return new RestaurantDTO(restaurant.getId().id().toString(), restaurant.getOwnerId().id().toString(),
                AddressDTO.from(restaurant.getAddress()), restaurant.getEmail(), restaurant.getPictureURL(),
                restaurant.getDefaultPrepTime(), restaurant.getCuisineType(),
                OpeningHoursDTO.from(restaurant.getOpeningHours()), restaurant.isOpen());
    }
}
