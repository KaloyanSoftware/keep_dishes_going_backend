package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.SaveRestaurantEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DomainEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private RestaurantId id;

    private OwnerId ownerId;

    private Address address;

    private String email;

    private String pictureURL;

    private Integer defaultPrepTime;

    private CuisineType cuisineType;

    private OpeningHours openingHours;

    private List<DomainEvent> events = new ArrayList<>();

    public Restaurant(RestaurantId id,
                      OwnerId ownerId,
                      Address address, String email,
                      String pictureURL, Integer defaultPrepTime,
                      CuisineType cuisineType, OpeningHours openingHours) {
        setId(id);
        setOwnerId(ownerId);
        setAddress(address);
        setEmail(email);
        setPictureURL(pictureURL);
        setDefaultPrepTime(defaultPrepTime);
        setCuisineType(cuisineType);
        setOpeningHours(openingHours);
    }

    public static Restaurant create(UUID ownerId,
                                     Address address, String email,
                                     String pictureURL, Integer defaultPrepTime,
                                     CuisineType cuisineType, OpeningHours openingHours){
        final Restaurant restaurant = new Restaurant(RestaurantId.create(),
                OwnerId.of(ownerId), address, email, pictureURL, defaultPrepTime,
                cuisineType, openingHours);

        restaurant.events.add(new SaveRestaurantEvent(restaurant.id.id(), address, email,
                pictureURL, defaultPrepTime, cuisineType.toString()));

        return restaurant;
    }

    public RestaurantId getId() {
        return id;
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

    public void setOwnerId(OwnerId ownerId) {
        this.ownerId = ownerId;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setDefaultPrepTime(Integer defaultPrepTime) {
        this.defaultPrepTime = defaultPrepTime;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public List<DomainEvent> getEvents() {
        return events;
    }
}
