package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.OrderProjectionNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.exceptions.RestaurantNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.ChangeOrderProjectionStatusCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.ChangeOrderProjectionStatusUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOrderProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateOrderProjectionPort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChangeOrderProjectionStatusUseCaseImpl implements ChangeOrderProjectionStatusUseCase {

    private final LoadOrderProjectionPort loadOrderProjectionPort;

    private final List<UpdateOrderProjectionPort> updateOrderProjectionPorts;

    private final LoadRestaurantPort loadRestaurantPort;

    public ChangeOrderProjectionStatusUseCaseImpl(final LoadOrderProjectionPort loadOrderProjectionPort,
                                                  final List<UpdateOrderProjectionPort> updateOrderProjectionPorts,
                                                  final LoadRestaurantPort loadRestaurantPort) {
        this.loadOrderProjectionPort = loadOrderProjectionPort;
        this.updateOrderProjectionPorts = updateOrderProjectionPorts;
        this.loadRestaurantPort = loadRestaurantPort;
    }

    @Override
    public OrderProjection changeStatus(final ChangeOrderProjectionStatusCommand command) {
        final OrderProjection orderProjection = loadOrderProjectionPort.loadBy(command.orderId()).orElseThrow(
                () -> new OrderProjectionNotFoundException("Order projection with id: " + command.orderId() + " not found!")
        );

        final Restaurant restaurant = loadRestaurantPort.loadBy(orderProjection.getRestaurantId()).orElseThrow(
                () -> new RestaurantNotFoundException("Restaurant with id: " + orderProjection.getRestaurantId() + " not found!")
        );

        switch (command.status()){
           case "ACCEPTED" -> orderProjection.accept(restaurant.getAddress());
           case "REJECTED" -> orderProjection.reject();
           case "READY" -> orderProjection.ready();
        }

        this.updateOrderProjectionPorts.forEach(updateOrderProjectionPort -> updateOrderProjectionPort.update(orderProjection));
        orderProjection.commitEvents();

        return orderProjection;
    }
}
