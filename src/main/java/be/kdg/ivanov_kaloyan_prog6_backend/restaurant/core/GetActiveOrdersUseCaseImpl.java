package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetActiveOrdersCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetActiveOrdersUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOrderProjectionPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetActiveOrdersUseCaseImpl implements GetActiveOrdersUseCase {

    private final LoadOrderProjectionPort loadOrderProjectionPort;

    public GetActiveOrdersUseCaseImpl(final LoadOrderProjectionPort loadOrderProjectionPort) {
        this.loadOrderProjectionPort = loadOrderProjectionPort;
    }

    @Override
    public List<OrderProjection> getActiveOrders(GetActiveOrdersCommand command) {
        return loadOrderProjectionPort.loadAllActiveBy(command.restaurantId());
    }
}
