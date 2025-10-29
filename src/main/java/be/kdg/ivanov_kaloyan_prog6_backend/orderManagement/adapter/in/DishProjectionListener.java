package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishBackInStockEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishOutOfStockEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishPublishedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishUnpublishedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishDeleteProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishSavedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishStockStatusChangeProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishStockStatusChangedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishSavedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishUnpublishedProjectionCommand;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class DishProjectionListener {

    private final DishSavedProjector saveProjector;

    private final DishDeleteProjector deleteProjector;

    private final DishStockStatusChangeProjector  dishStockStatusChangeProjector;

    public DishProjectionListener(final DishSavedProjector saveProjector,
                                  final DishDeleteProjector deleteProjector,
                                  final DishStockStatusChangeProjector dishStockStatusChangeProjector) {
        this.saveProjector = saveProjector;
        this.deleteProjector = deleteProjector;
        this.dishStockStatusChangeProjector = dishStockStatusChangeProjector;
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

    @EventListener(DishOutOfStockEvent.class)
    public void onDishOutOfStock(DishOutOfStockEvent event) {
        if(event.published()){

            final DishStockStatusChangedProjectionCommand command = new DishStockStatusChangedProjectionCommand(event.dishId(), event.stockStatus());

            this.dishStockStatusChangeProjector.project(command);
        }
    }

    @EventListener(DishBackInStockEvent.class)
    public void onDishOutOfStock(DishBackInStockEvent event) {
        if(event.published()){

            final DishStockStatusChangedProjectionCommand command = new DishStockStatusChangedProjectionCommand(event.dishId(), event.stockStatus());

            this.dishStockStatusChangeProjector.project(command);
        }
    }
}
