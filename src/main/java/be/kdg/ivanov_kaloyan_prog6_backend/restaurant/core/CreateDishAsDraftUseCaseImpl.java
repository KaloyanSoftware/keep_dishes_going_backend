package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.RestaurantId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateDishAsDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateDishAsDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveDishDraftPort;
import org.springframework.stereotype.Service;

@Service
public class CreateDishAsDraftUseCaseImpl implements CreateDishAsDraftUseCase {

    private final SaveDishDraftPort saveDishDraftPort;

    public CreateDishAsDraftUseCaseImpl(final SaveDishDraftPort saveDishDraftPort) {
        this.saveDishDraftPort = saveDishDraftPort;
    }

    @Override
    public DishDraft createDishAsDraft(CreateDishAsDraftCommand command) {

        DishDraft draft = new DishDraft(
                RestaurantId.of(command.restaurantId()),
                command.name(),
                command.type(),
                command.tags(),
                command.description(),
                command.price(),
                command.pictureURL()
        );

        return saveDishDraftPort.save(draft);
    }
}
