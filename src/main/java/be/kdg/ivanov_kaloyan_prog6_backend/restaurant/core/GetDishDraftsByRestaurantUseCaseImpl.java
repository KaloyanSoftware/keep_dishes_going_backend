package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetDishDraftsForRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetDishDraftsByRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadDishDraftPort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetDishDraftsByRestaurantUseCaseImpl implements GetDishDraftsByRestaurantUseCase {

    private final LoadDishDraftPort loadDishDraftPort;

    public GetDishDraftsByRestaurantUseCaseImpl(final LoadDishDraftPort loadDishDraftPort) {
        this.loadDishDraftPort = loadDishDraftPort;
    }

    @Override
    public List<DishDraft> getDishDrafts(GetDishDraftsForRestaurantCommand command) {
        return loadDishDraftPort.loadByRestaurant(command.restaurantId());

    }
}
