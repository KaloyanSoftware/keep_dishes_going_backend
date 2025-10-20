package be.kdg.ivanov_kaloyan_prog6_backend.common.events;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.AddressDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import java.time.LocalDateTime;
import java.util.UUID;

public record SaveRestaurantEvent(LocalDateTime eventPit, UUID id, AddressDTO address, String email, String pictureURL,
                                  Integer defaultPrepTime, String cuisineType) implements DomainEvent {

    public SaveRestaurantEvent(LocalDateTime eventPit, UUID id, AddressDTO address, String email,
                               String pictureURL, Integer defaultPrepTime, String cuisineType) {
        this.eventPit = eventPit;
        this.id = id;
        this.address = address;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
    }

    public SaveRestaurantEvent(UUID id, Address address, String email, String pictureURL,
                               Integer defaultPrepTime, String cuisineType) {
        this(LocalDateTime.now(), id, AddressDTO.from(address), email, pictureURL, defaultPrepTime, cuisineType);
    }

    @Override
    public LocalDateTime eventPit() {
        return eventPit;
    }
}
