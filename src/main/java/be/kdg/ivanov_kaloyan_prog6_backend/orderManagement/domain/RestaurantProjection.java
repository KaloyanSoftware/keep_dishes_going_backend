package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public class RestaurantProjection {
    private UUID restaurantId;

    private Location location;

    private String email;

    private String pictureURL;

    private Integer defaultPrepTime;

    private String cuisineType;

    public RestaurantProjection(UUID restaurantId, Location location, String email,
                                String pictureURL, Integer defaultPrepTime, String cuisineType) {
        this.restaurantId = restaurantId;
        this.location = location;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
    }

    public static RestaurantProjection create(UUID restaurantId, Location location, String email,
                                              String pictureURL, Integer defaultPrepTime, String cuisineType){
        return new RestaurantProjection(restaurantId,  location, email, pictureURL, defaultPrepTime, cuisineType);
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
}
