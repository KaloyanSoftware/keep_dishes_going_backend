package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.OrderNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.GetOrderCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.GetOrderUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadOrderPort;
import org.springframework.stereotype.Service;

@Service
public class GetOrderUseCaseImpl implements GetOrderUseCase {

    private final LoadOrderPort loadOrderPort;

    public GetOrderUseCaseImpl(final LoadOrderPort loadOrderPort) {
        this.loadOrderPort = loadOrderPort;
    }

    @Override
    public Order getBy(GetOrderCommand command) {

        return loadOrderPort.loadBy(command.orderId()).orElseThrow(
                () -> new OrderNotFoundException("Order with id: " + command.orderId() + " not found!")
        );
    }
}
