package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderAcceptedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderReadyForPickUpEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.OrderRejectedEvent;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ChangeOrderStatusCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ChangeOrderStatusUseCase;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusListener {

    private final ChangeOrderStatusUseCase changeOrderStatusUseCase;

    public OrderStatusListener(final ChangeOrderStatusUseCase changeOrderStatusUseCase) {
        this.changeOrderStatusUseCase = changeOrderStatusUseCase;
    }

    @ApplicationModuleListener
    public void onOrderAccepted(OrderAcceptedEvent event){
        final ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(event.orderId(), Order.OrderStatus.ACCEPTED);
        this.changeOrderStatusUseCase.changeStatus(command);
    }

    @ApplicationModuleListener
    public void onOrderRejected(OrderRejectedEvent event){
        final ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(event.orderId(), Order.OrderStatus.REJECTED);
        this.changeOrderStatusUseCase.changeStatus(command);
    }

    @ApplicationModuleListener
    public void onOrderReady(OrderReadyForPickUpEvent event){
        final ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(event.orderId(), Order.OrderStatus.READY);
        this.changeOrderStatusUseCase.changeStatus(command);
    }
}
