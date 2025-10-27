package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.OpenRestaurantCommand;

public interface OpenRestaurantUseCase {
    Restaurant open(OpenRestaurantCommand command);
}
