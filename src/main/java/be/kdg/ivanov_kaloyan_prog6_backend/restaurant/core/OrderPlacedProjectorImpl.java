package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.OrderPlacedProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.OrderPlacedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateOrderProjectionPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderPlacedProjectorImpl implements OrderPlacedProjector {

    private final UpdateOrderProjectionPort updateOrderProjectionPort;

    public OrderPlacedProjectorImpl(final @Qualifier("jpa") UpdateOrderProjectionPort updateOrderProjectionPort) {
        this.updateOrderProjectionPort = updateOrderProjectionPort;
    }


    @Override
    public void project(OrderPlacedProjectionCommand command) {
        updateOrderProjectionPort.update(new OrderProjection(command.orderId(), command.restaurantId(),
                command.orderLines(), command.deliveryInfo()));
    }
}
