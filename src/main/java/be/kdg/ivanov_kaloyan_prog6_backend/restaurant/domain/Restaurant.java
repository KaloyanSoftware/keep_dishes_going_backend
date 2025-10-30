package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import be.kdg.ivanov_kaloyan_prog6_backend.common.dto.DeliveryInfoDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class Restaurant {

    private static final Logger log = LoggerFactory.getLogger(Restaurant.class);
    private final RestaurantId id;
    private final OwnerId ownerId;
    private Address address;
    private String email;
    private String pictureURL;
    private Integer defaultPrepTime;
    private CuisineType cuisineType;
    private OpeningHours openingHours;
    private boolean isOpen = true;
    private final List<DomainEvent> eventStore = new ArrayList<>();
    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();


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
                      CuisineType cuisineType, OpeningHours openingHours, List<DomainEvent> eventStore, boolean isOpen) {
        this(id, ownerId, address, email, pictureURL, defaultPrepTime, cuisineType, openingHours);
        this.isOpen = isOpen;
        this.eventStore.addAll(eventStore);
    }

    public static Restaurant create(UUID ownerId,
                                    Address address, String email,
                                    String pictureURL, Integer defaultPrepTime,
                                    CuisineType cuisineType, OpeningHours openingHours) {
        final Restaurant restaurant = new Restaurant(RestaurantId.create(),
                OwnerId.of(ownerId), address, email, pictureURL, defaultPrepTime,
                cuisineType, openingHours);

        restaurant.getUncommitedEvents().add(new SaveRestaurantEvent(address, restaurant.id.id() ,email,
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

    public List<DomainEvent> getDomainEvents() {
        return new ArrayList<>(Stream.concat(eventStore.stream(), uncommittedEvents.stream()).toList());
    }

    public List<DomainEvent> getUncommitedEvents(){
        return new ArrayList<>(uncommittedEvents);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open(){
        this.isOpen = true;
        this.uncommittedEvents.add(new RestaurantOpenEvent(this.id.id()));
    }

    public void close(){
        this.isOpen = false;
        this.uncommittedEvents.add(new RestaurantCloseEvent(this.id.id()));
    }

    public void accept(OrderProjection order, String status){
        log.error("Order with restaurantId (current object restaurantId) id: {}, accepted!", id.id());
        log.error("Order with restaurantId (getter restaurantId) id: {}, accepted!", order.getRestaurantId());
        order.changeStatus(status);
        this.uncommittedEvents.add(new OrderAcceptedEvent(id.id(), order.getOrderId(),
                DeliveryInfoDTO.from(address.street(), address.number().toString(),
                        address.postalCode(), address.city()),
                DeliveryInfoDTO.from(order.getDeliveryInfo().street(), order.getDeliveryInfo().number(),
                        order.getDeliveryInfo().postalCode(), order.getDeliveryInfo().city())));
    }

    public void reject(OrderProjection order, String status){
        order.changeStatus(status);
        this.uncommittedEvents.add(new OrderRejectedEvent(order.getOrderId()));
    }

    public void ready(OrderProjection order, String status){
        order.changeStatus(status);
        this.uncommittedEvents.add(new OrderReadyForPickUpEvent(id.id(), order.getOrderId()));
    }

    public void pickedUp(OrderProjection order, UUID eventId, LocalDateTime occurredAt, String status){
        order.changeStatus(status);
        this.uncommittedEvents.add(new OrderPickedUpEvent(eventId, occurredAt ,id.id(), order.getOrderId()));
    }

    public void delivered(OrderProjection order, UUID eventId, LocalDateTime occurredAt, String status){
        order.changeStatus(status);
        this.uncommittedEvents.add(new OrderDeliveredEvent(eventId, occurredAt ,id.id(), order.getOrderId()));
    }

    public void commitEvents(){
        this.eventStore.addAll(uncommittedEvents);
        uncommittedEvents.clear();
    }
}
