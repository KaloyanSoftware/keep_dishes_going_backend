package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.CreateOrderCommand;

public interface CreateOrderUseCase {
    Order create(CreateOrderCommand command);
}
