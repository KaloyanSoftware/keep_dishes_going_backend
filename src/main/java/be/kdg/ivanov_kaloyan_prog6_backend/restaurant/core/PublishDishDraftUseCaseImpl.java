package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.DraftNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.MenuNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.PublishDishDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.PublishDishDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.DeleteDishDraftPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadDishDraftPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PublishDishDraftUseCaseImpl implements PublishDishDraftUseCase {

    private final LoadDishDraftPort loadDishDraftPort;

    private final LoadMenuPort loadMenuPort;

    private final List<UpdateMenuPort> updateMenuPorts;

    private final DeleteDishDraftPort deleteDishDraftPort;

    public PublishDishDraftUseCaseImpl(final LoadDishDraftPort loadDishDraftPort,
                                       final LoadMenuPort loadMenuPort,
                                       final List<UpdateMenuPort> updateMenuPorts,
                                       final DeleteDishDraftPort deleteDishDraftPort) {
        this.loadDishDraftPort = loadDishDraftPort;
        this.loadMenuPort = loadMenuPort;
        this.updateMenuPorts = updateMenuPorts;
        this.deleteDishDraftPort = deleteDishDraftPort;
    }

    @Override
    public Dish publish(PublishDishDraftCommand command) {
        DishDraft draft = loadDishDraftPort.loadBy(command.draftId()).orElseThrow(
                () -> new DraftNotFoundException("Can't find dish draft with id: " + command.draftId())
        );

        Menu menu = loadMenuPort.loadByRestaurantId(command.restaurantId()).orElseThrow(
                () -> new MenuNotFoundException("Can't find menu with restaurant id: " + command.restaurantId())
        );

        Dish dish = menu.publishDraft(draft);

        deleteDishDraftPort.delete(draft.getId().id());

        this.updateMenuPorts.forEach(port -> port.update(menu));
        menu.commitEvents();

        return dish;
    }
}
