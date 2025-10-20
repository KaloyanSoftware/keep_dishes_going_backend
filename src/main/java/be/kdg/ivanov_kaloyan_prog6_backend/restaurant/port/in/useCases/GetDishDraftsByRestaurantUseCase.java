package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetDishDraftsForRestaurantCommand;

import java.util.List;

public interface GetDishDraftsByRestaurantUseCase {

    List<DishDraft> getDishDrafts(GetDishDraftsForRestaurantCommand command);
}
