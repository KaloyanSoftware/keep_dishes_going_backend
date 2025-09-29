package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.util.UUID;

//aggregate
public class Restaurant {

    private RestaurantId restaurantId;

    //does the restaurant need to have access to the owner? (bi-directional?)
    //private Owner owner;

    private Address address;

    private String email;

    private String pictureURL;

    private Double defaultPrepTime;

    private CuisineType cuisineType;

    private OpeningHours openingHours;

    private Menu menu = new Menu();

    private static final int MAX_MENU_DISHES = 10;

    public Restaurant(Address address, String email, String pictureURL, Double defaultPrepTime, CuisineType cuisineType, OpeningHours openingHours) {
        this.restaurantId = RestaurantId.create();
        this.address = address;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
    }

    public RestaurantId retrieveRestaurantId() {
        return restaurantId;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", address=" + address +
                ", email='" + email + '\'' +
                '}';
    }

    public void markDishOutOfStock(UUID dishId){
       menu.markDishOutOfStock(dishId);
    }
}
