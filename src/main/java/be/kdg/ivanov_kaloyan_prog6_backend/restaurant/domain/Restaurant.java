package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.SaveRestaurantEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DomainEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private final RestaurantId id;
    private final OwnerId ownerId;
    private Address address;
    private String email;
    private String pictureURL;
    private Integer defaultPrepTime;
    private CuisineType cuisineType;
    private OpeningHours openingHours;
    private final List<DomainEvent> events = new ArrayList<>();

    public Restaurant(RestaurantId id,
                      OwnerId ownerId,
                      Address address, String email,
                      String pictureURL, Integer defaultPrepTime,
                      CuisineType cuisineType, OpeningHours openingHours) {
        this.id = id;
        this.ownerId = ownerId;
        this.address = address;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
    }

    public Restaurant(RestaurantId id,
                      OwnerId ownerId,
                      Address address, String email,
                      String pictureURL, Integer defaultPrepTime,
                      CuisineType cuisineType, OpeningHours openingHours, List<DomainEvent> events) {
        this.id = id;
        this.ownerId = ownerId;
        this.address = address;
        this.email = email;
        this.pictureURL = pictureURL;
        this.defaultPrepTime = defaultPrepTime;
        this.cuisineType = cuisineType;
        this.openingHours = openingHours;
        this.events.addAll(events);
    }

    public static Restaurant create(UUID ownerId,
                                    Address address, String email,
                                    String pictureURL, Integer defaultPrepTime,
                                    CuisineType cuisineType, OpeningHours openingHours) {
        final Restaurant restaurant = new Restaurant(RestaurantId.create(),
                OwnerId.of(ownerId), address, email, pictureURL, defaultPrepTime,
                cuisineType, openingHours);

        restaurant.events.add(new SaveRestaurantEvent(address, restaurant.id.id() ,email,
                pictureURL, defaultPrepTime, cuisineType.toString()));

        return restaurant;
    }

    public RestaurantId getId() {
        return id;
    }

    public OwnerId getOwnerId() {
        return ownerId;
    }

    public Address getAddress() {
        return address;
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

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public List<DomainEvent> getEvents() {
        return events;
    }


}
