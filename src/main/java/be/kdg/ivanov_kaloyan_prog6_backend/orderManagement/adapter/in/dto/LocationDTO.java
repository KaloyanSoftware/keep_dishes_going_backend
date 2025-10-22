package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Location;

public record LocationDTO(String street,
                          Integer number,
                          String postalCode,
                          String city,
                          String country) {

    public static LocationDTO create(String street, Integer number, String postalCode, String city, String country) {
        return new  LocationDTO(street, number, postalCode, city, country);
    }

    public static LocationDTO from(final Location location) {
        return new LocationDTO(
                location.street(),
                location.number(),
                location.postalCode(),
                location.city(),
                location.country()
        );
    }

}
