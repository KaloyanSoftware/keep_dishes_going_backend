package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishPublishedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishUnpublishedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishDeleteProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishSavedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishSavedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishUnpublishedProjectionCommand;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DishProjectionListener {

    private final DishSavedProjector saveProjector;

    private final DishDeleteProjector deleteProjector;

    public DishProjectionListener(final DishSavedProjector saveProjector,
                                  final DishDeleteProjector deleteProjector) {
        this.saveProjector = saveProjector;
        this.deleteProjector = deleteProjector;
    }

    @EventListener(DishPublishedEvent.class)
    public void onDishPublished(DishPublishedEvent event) {

        final DishSavedProjectionCommand command = new DishSavedProjectionCommand(event.dishId(), event.restaurantId(),event.stockStatus(), event.name(), event.type(),
                event.tags(), event.description(), event.price(), event.pictureURL());

        this.saveProjector.project(command);
    }

    @EventListener(DishUnpublishedEvent.class)
    public void onDishUnpublished(DishUnpublishedEvent event) {

        final DishUnpublishedProjectionCommand command = new DishUnpublishedProjectionCommand(event.dishId());

        this.deleteProjector.project(command);
    }
}
