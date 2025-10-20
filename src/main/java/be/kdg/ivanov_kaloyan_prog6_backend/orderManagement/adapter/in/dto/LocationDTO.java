package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

public record LocationDTO(String street,
                          Integer number,
                          String postalCode,
                          String city,
                          String country) {

    public static LocationDTO create(String street, Integer number, String postalCode, String city, String country) {
        return new  LocationDTO(street, number, postalCode, city, country);
    }

}
