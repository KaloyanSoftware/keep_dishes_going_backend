package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderPlacedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DeliveryInfo;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.OrderPlacedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.OrderPlacedProjectionCommand;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class OrderProjectionEventListener {

    private final OrderPlacedProjector projector;

    public OrderProjectionEventListener(final OrderPlacedProjector projector) {
        this.projector = projector;
    }

    @ApplicationModuleListener
    public void onOrderPlaced(OrderPlacedEvent event) {

        final DeliveryInfo deliveryInfo = new DeliveryInfo(event.street(), event.number(), event.city(), event.postalCode());

        final OrderPlacedProjectionCommand command = new OrderPlacedProjectionCommand(event.orderId(), event.restaurantId(),
                event.orderLines(), deliveryInfo);

        this.projector.project(command);
    }

}
