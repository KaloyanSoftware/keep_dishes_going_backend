package be.kdg.ivanov_kaloyan_prog6_backend.common.dto;

public record DeliveryInfoDTO(String street, String number, String postalCode, String city) {

    public static DeliveryInfoDTO from(String street, String number, String postalCode, String city){
        return new DeliveryInfoDTO(street, number, postalCode, city);
    }

}
