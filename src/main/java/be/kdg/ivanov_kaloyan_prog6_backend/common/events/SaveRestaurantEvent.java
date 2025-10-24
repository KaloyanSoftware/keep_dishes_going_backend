package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.AddressDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record SaveRestaurantEvent(LocalDateTime eventPit, UUID id, UUID restaurantId, EventCatalog eventCatalog, AddressDTO address, String email, String pictureURL,
                                  Integer defaultPrepTime, String cuisineType) implements DomainEvent {

    public SaveRestaurantEvent(Address address, UUID restaurantId ,String email, String pictureURL,
                               Integer defaultPrepTime, String cuisineType) {
        this(LocalDateTime.now(), UUID.randomUUID(), restaurantId,EventCatalog.RESTAURANT_SAVED, AddressDTO.from(address), email, pictureURL, defaultPrepTime, cuisineType);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
