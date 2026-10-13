package be.kdg.ivanov_kaloyan_prog6_backend.restaurant;

public record Address(
        String street,
        Integer number,
        String postalCode,
        String city,
        String country
) {
}
