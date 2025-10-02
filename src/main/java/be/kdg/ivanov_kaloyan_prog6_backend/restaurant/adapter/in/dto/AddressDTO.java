package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Address;

public record AddressDTO(String street,
                         Integer number,
                         String postalCode,
                         String city,
                         String country) {

    public static AddressDTO from(final Address address) {
        return new AddressDTO(address.street(), address.number(),
                address.postalCode(), address.city(), address.country());
    }
}
