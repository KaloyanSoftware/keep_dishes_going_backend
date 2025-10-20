package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetRestaurantForOwnerCommand;

public interface GetRestaurantForOwnerUseCase {

    Restaurant getRestaurant(GetRestaurantForOwnerCommand command);
}
