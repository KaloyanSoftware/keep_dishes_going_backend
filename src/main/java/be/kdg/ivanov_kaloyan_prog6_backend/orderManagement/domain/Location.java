package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

public record Location(String street,
                       Integer number,
                       String postalCode,
                       String city,
                       String country) {

    public static Location create(String street, Integer number, String postalCode, String city, String country) {
        return new Location(street, number, postalCode, city, country);
    }
}
