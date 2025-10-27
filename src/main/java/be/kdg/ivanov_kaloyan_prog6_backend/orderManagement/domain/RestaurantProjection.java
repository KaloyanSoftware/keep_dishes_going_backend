package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.OrderPlacedException;

import java.util.UUID;

public class RestaurantProjection {
    private UUID restaurantId;

    private Location location;

    private String email;

    private String pictureURL;

    private Integer defaultPrepTime;

    private String cuisineType;

    private boolean isOpen;

    public RestaurantProjection(UUID restaurantId, Location location, String email,
                                String pictureURL, Integer defaultPrepTime, String cuisineType) {
        this.restaurantId = restaurantId;
        this.location = location;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
        this.isOpen = true;
    }

    public static RestaurantProjection create(UUID restaurantId, Location location, String email,
                                              String pictureURL, Integer defaultPrepTime, String cuisineType){
        return new RestaurantProjection(restaurantId,  location, email, pictureURL, defaultPrepTime, cuisineType);
    }

    public static RestaurantProjection rehydrate(UUID restaurantId, Location location,
                                                 String email, String pictureURL,
                                                 Integer defaultPrepTime, String cuisineType,
                                                 boolean isOpen) {
        RestaurantProjection projection = new RestaurantProjection(
                restaurantId, location, email, pictureURL, defaultPrepTime, cuisineType
        );
        projection.isOpen = isOpen;
        return projection;
    }

    public void checkOrderAvailability(){
        if(!isOpen){
            throw new OrderPlacedException("Restaurant with id: " + restaurantId + " is not open, can't place any orders!");
        }
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public Integer getDefaultPrepTime() {
        return defaultPrepTime;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
