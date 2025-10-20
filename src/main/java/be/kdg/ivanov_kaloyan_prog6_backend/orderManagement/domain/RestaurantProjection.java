package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.util.UUID;

public class RestaurantProjection {
    private RestaurantProjectionId restaurantId;

    private Location location;

    private String email;

    private String pictureURL;

    private Integer defaultPrepTime;

    private Cuisine cuisine;

    public RestaurantProjection(RestaurantProjectionId restaurantId, Location location, String email,
                                String pictureURL, Integer defaultPrepTime, Cuisine cuisine) {
        this.restaurantId = restaurantId;
        this.location = location;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisine = cuisine;
    }

    public static RestaurantProjection create(UUID restaurantId, Location location, String email,
                                              String pictureURL, Integer defaultPrepTime, Cuisine cuisine){
        return new RestaurantProjection(RestaurantProjectionId.of(restaurantId),  location, email, pictureURL, defaultPrepTime, cuisine);
    }

    public RestaurantProjectionId getRestaurantId() {
        return restaurantId;
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

    public Cuisine getCuisine() {
        return cuisine;
    }
}
