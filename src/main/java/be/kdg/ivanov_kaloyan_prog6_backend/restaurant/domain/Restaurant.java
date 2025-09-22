package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

public class Restaurant {
    private Owner owner;

    private Address address;

    private String email;

    private String pictureURL;

    private Double defaultPrepTime;

    private CuisineType cuisineType;

    private OpeningHours openingHours;

    public Restaurant(Owner owner, Address address, String email, String pictureURL, Double defaultPrepTime, CuisineType cuisineType, OpeningHours openingHours) {
        this.owner = owner;
        this.address = address;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
    }
}
