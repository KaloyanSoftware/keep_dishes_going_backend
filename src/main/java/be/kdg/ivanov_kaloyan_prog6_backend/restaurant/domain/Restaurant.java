package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.UUID;

//aggregate
public class Restaurant {

    private RestaurantId id;

    private OwnerId ownerId;

    private Address address;

    private String email;

    private String pictureURL;

    private Integer defaultPrepTime;

    private CuisineType cuisineType;

    private OpeningHours openingHours;

    private Menu menu = new Menu();

    private static final int MAX_MENU_DISHES = 10;

    public Restaurant(OwnerId ownerId,
                      Address address, String email,
                      String pictureURL, Integer defaultPrepTime,
                      CuisineType cuisineType, OpeningHours openingHours) {
        this.id = RestaurantId.create();
        this.ownerId = ownerId;
        this.address = address;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
    }

    public RestaurantId getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + id +
                ", address=" + address +
                ", email='" + email + '\'' +
                '}';
    }

    public Address getAddress() {
        return address;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public String getEmail() {
        return email;
    }

    public Integer getDefaultPrepTime() {
        return defaultPrepTime;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setId(RestaurantId id) {
        this.id = id;
    }

    public OwnerId getOwnerId() {
        return ownerId;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public Menu getMenu() {
        return menu;
    }
}
