package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.OrderNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ChangeOrderStatusUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadOrderPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateOrderPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ChangeOrderStatusCommand;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Transactional
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
                () -> new OrderNotFoundException("Order with id: " + command.orderId() + " not found!")
        );

        switch (command.status()){
            case ACCEPTED -> order.accepted(command.status());
            case REJECTED -> order.rejected(command.status());
            case READY -> order.ready(command.status());
            case PICKED_UP -> order.pickedUp(command.status());
            case DELIVERED -> order.delivered(command.status());
        }

        updateOrderPort.update(order);
    }
}
