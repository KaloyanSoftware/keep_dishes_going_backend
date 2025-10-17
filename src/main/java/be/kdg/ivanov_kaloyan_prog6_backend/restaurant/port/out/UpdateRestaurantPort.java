package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;

public interface SaveRestaurantPort {

    Restaurant save(Restaurant restaurant);
}
