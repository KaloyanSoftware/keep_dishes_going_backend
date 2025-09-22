package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

public record AddressDTO(String street,
                         Integer number,
                         String postalCode,
                         String city,
                         String country) {
}
