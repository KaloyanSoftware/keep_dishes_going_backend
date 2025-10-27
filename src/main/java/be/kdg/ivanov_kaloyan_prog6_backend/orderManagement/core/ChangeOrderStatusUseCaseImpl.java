package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ChangeOrderStatusUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadOrderPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateOrderPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.OrderProjectionNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ChangeOrderStatusCommand;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChangeOrderStatusUseCaseImpl implements ChangeOrderStatusUseCase {
    private final UpdateOrderPort updateOrderPort;

    private final LoadOrderPort loadOrderPort;

    public ChangeOrderStatusUseCaseImpl(final @Qualifier("jpa") UpdateOrderPort updateOrderPort,
                                        final LoadOrderPort loadOrderPort) {
        this.updateOrderPort = updateOrderPort;
        this.loadOrderPort = loadOrderPort;
    }

    @Override
    public void changeStatus(ChangeOrderStatusCommand command) {
        final Order order = loadOrderPort.loadBy(command.orderId()).orElseThrow(
                () -> new OrderProjectionNotFoundException("Order with id: " + command.orderId() + " not found!")
        );

        switch (command.status()){
            case ACCEPTED -> order.accepted();
            case REJECTED -> order.rejected();
        }

        updateOrderPort.update(order);
    }
}
