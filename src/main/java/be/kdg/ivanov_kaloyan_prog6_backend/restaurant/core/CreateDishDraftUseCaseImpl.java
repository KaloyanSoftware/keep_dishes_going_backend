package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.RestaurantId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateDishDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateDishDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveDishDraftPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class CreateDishDraftUseCaseImpl implements CreateDishDraftUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateDishDraftUseCaseImpl.class);
    private final SaveDishDraftPort saveDishDraftPort;



    public CreateDishDraftUseCaseImpl(final SaveDishDraftPort saveDishDraftPort) {
        this.saveDishDraftPort = saveDishDraftPort;
    }

    @Override
    public DishDraft create(CreateDishDraftCommand command) {

        log.error(String.valueOf(command.tags().size()));

        DishDraft draft = new DishDraft(
                RestaurantId.of(command.restaurantId()),
                DishId.of(command.dishId()),
                command.name(),
                command.type(),
                command.tags(),
                command.description(),
                BigDecimal.valueOf(command.price()),
                command.pictureURL()
        );

        return saveDishDraftPort.save(draft);
    }
}
