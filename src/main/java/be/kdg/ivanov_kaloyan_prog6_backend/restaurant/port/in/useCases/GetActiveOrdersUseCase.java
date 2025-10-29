package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetActiveOrdersCommand;
import java.util.List;

public interface GetActiveOrdersUseCase {
    List<OrderProjection> getActiveOrders(GetActiveOrdersCommand command);
}
