package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.*;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ChangeOrderStatusCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ChangeOrderStatusUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusListener {

    private static final Logger log = LoggerFactory.getLogger(OrderStatusListener.class);
    private final ChangeOrderStatusUseCase changeOrderStatusUseCase;



    public OrderStatusListener(final ChangeOrderStatusUseCase changeOrderStatusUseCase) {
        this.changeOrderStatusUseCase = changeOrderStatusUseCase;
    }

    @EventListener(OrderAcceptedEvent.class)
    public void onOrderAccepted(OrderAcceptedEvent event){
        log.error("Order accepted event received with orderId: {}", event.orderId());
        final ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(event.orderId(), Order.OrderStatus.ACCEPTED);
        this.changeOrderStatusUseCase.changeStatus(command);
    }

    @EventListener(OrderRejectedEvent.class)
    public void onOrderRejected(OrderRejectedEvent event){
        log.error("Order rejected event received with orderId: {}", event.orderId());
        final ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(event.orderId(), Order.OrderStatus.REJECTED);
        this.changeOrderStatusUseCase.changeStatus(command);
    }

    @EventListener(OrderReadyForPickUpEvent.class)
    public void onOrderReady(OrderReadyForPickUpEvent event){
        log.error("Order ready event received with orderId: {}", event.orderId());
        final ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(event.orderId(), Order.OrderStatus.READY);
        this.changeOrderStatusUseCase.changeStatus(command);
    }

    @EventListener(OrderPickedUpEvent.class)
    public void onOrderPickedUp(OrderPickedUpEvent event){
        log.error("Order picked up event received with orderId: {}", event.orderId());
        final ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(event.orderId(), Order.OrderStatus.PICKED_UP);
        this.changeOrderStatusUseCase.changeStatus(command);
    }

    @EventListener(OrderDeliveredEvent.class)
    public void onOrderDelivered(OrderDeliveredEvent event){
        log.error("Order delivered event received with orderId: {}", event.orderId());
        final ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(event.orderId(), Order.OrderStatus.DELIVERED);
        this.changeOrderStatusUseCase.changeStatus(command);
    }

}
