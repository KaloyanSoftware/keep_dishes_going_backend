package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.GetAllDishesForRestaurantCommand;

import java.util.List;

public interface GetAllDishProjectionsForRestaurantUseCase {
    List<DishProjection> getAllForRestaurant(GetAllDishesForRestaurantCommand command);
}
