package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.config.RabbitMQTopology;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderDeliveredEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderPickedUpEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderPlacedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DeliveryInfo;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.ChangeOrderDeliveryStatusProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.OrderPlacedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.ChangeOrderProjectionDeliveryStatusCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.OrderPlacedProjectionCommand;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderProjectionEventListener {

    private final OrderPlacedProjector projector;

    private final ChangeOrderDeliveryStatusProjector changeOrderDeliveryStatusProjector;

    public OrderProjectionEventListener(final OrderPlacedProjector projector,
                                        final ChangeOrderDeliveryStatusProjector changeOrderDeliveryStatusProjector) {
        this.projector = projector;
        this.changeOrderDeliveryStatusProjector = changeOrderDeliveryStatusProjector;
    }

    @EventListener(OrderPlacedEvent.class)
    public void onOrderPlaced(OrderPlacedEvent event) {

        final DeliveryInfo deliveryInfo = new DeliveryInfo(event.street(), event.number(), event.postalCode(), event.city());

        final OrderPlacedProjectionCommand command = new OrderPlacedProjectionCommand(event.orderId(), event.restaurantId(),
                event.orderLines(), deliveryInfo);

        this.projector.project(command);
    }

    @RabbitListener(queues = RabbitMQTopology.ORDER_PICKEDUP_QUEUE)
    public void onOrderPickedUp(OrderPickedUpEvent event){
        final ChangeOrderProjectionDeliveryStatusCommand command = new ChangeOrderProjectionDeliveryStatusCommand(event.eventId(), event.occurredAt(),
                event.restaurantId(), event.orderId(), "PICKED_UP");

        this.changeOrderDeliveryStatusProjector.changeDeliveryStatus(command);
    }

    @RabbitListener(queues = RabbitMQTopology.ORDER_DELIVERED_QUEUE)
    public void onOrderDelivered(OrderDeliveredEvent event){
        final ChangeOrderProjectionDeliveryStatusCommand command = new ChangeOrderProjectionDeliveryStatusCommand(event.eventId(), event.occurredAt(),
                event.restaurantId(), event.orderId(), "DELIVERED");

        this.changeOrderDeliveryStatusProjector.changeDeliveryStatus(command);
    }

}
