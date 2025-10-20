package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.DishPublishedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.FoodTagProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishSavedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishSavedProjectionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DishProjectionListener {

    private static final Logger log = LoggerFactory.getLogger(DishProjectionListener.class);
    private final DishSavedProjector projector;



    public DishProjectionListener(DishSavedProjector projector) {
        this.projector = projector;
    }

    @EventListener(DishPublishedEvent.class)
    public void onDishPublished(DishPublishedEvent event) {

        log.error("Dish with id : {} projection save event received!", event.dishId());

        final List<FoodTagProjection> tags = event.tags().stream()
                .map(FoodTagProjection::valueOf).toList();

        final DishSavedProjectionCommand command = new DishSavedProjectionCommand(event.dishId(), event.restaurantId(),
                DishProjection.StockStatus.valueOf(event.stockStatus()), event.name(), DishProjection.DishType.valueOf(event.type()),
                tags, event.description(), event.price(), event.pictureURL());

        this.projector.project(command);
    }
}
