package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

public class Owner {
    private OwnerId ownerId;

    private Restaurant restaurant;

    void assignRestaurant(Restaurant restaurant){
        this.restaurant = restaurant;
    }
}
