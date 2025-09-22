package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

public record Address(
        String street,
        Integer number,
        String postalCode,
        String city,
        String country
) {
}
