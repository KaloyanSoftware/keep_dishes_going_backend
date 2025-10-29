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
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class ChangeOrderProjectionStatusUseCaseImpl implements ChangeOrderProjectionStatusUseCase {

    private final LoadOrderProjectionPort loadOrderProjectionPort;

    private final UpdateOrderProjectionPort updateOrderProjectionPort;

    private final List<UpdateRestaurantPort> updateRestaurantPorts;

    private final LoadRestaurantPort loadRestaurantPort;

    public ChangeOrderProjectionStatusUseCaseImpl(final LoadOrderProjectionPort loadOrderProjectionPort,
                                                  final UpdateOrderProjectionPort updateOrderProjectionPort,
                                                  final LoadRestaurantPort loadRestaurantPort,
                                                  final List<UpdateRestaurantPort> updateRestaurantPorts) {
        this.loadOrderProjectionPort = loadOrderProjectionPort;
        this.updateOrderProjectionPort = updateOrderProjectionPort;
        this.loadRestaurantPort = loadRestaurantPort;
        this.updateRestaurantPorts = updateRestaurantPorts;
    }

    @Override
    public OrderProjection changeStatus(final ChangeOrderProjectionStatusCommand command) {
        final OrderProjection order = loadOrderProjectionPort.loadBy(command.orderId()).orElseThrow(
                () -> new OrderProjectionNotFoundException("Order projection with id: " + command.orderId() + " not found!")
        );

        final Restaurant restaurant = loadRestaurantPort.loadBy(order.getRestaurantId()).orElseThrow(
                () -> new RestaurantNotFoundException("Restaurant with id: " + order.getRestaurantId() + " not found!")
        );

        switch (command.status()){
           case "ACCEPTED" -> restaurant.accept(order, command.status());
           case "REJECTED" -> restaurant.reject(order,command.status());
           case "READY" -> restaurant.ready(order, command.status());
           case "PICKED_UP" -> restaurant.pickedUp(order, command.status());
           case "DELIVERED" -> restaurant.delivered(order, command.status());
        }

        this.updateRestaurantPorts.forEach(updateRestaurantPort -> updateRestaurantPort.update(restaurant));
        restaurant.commitEvents();

        this.updateOrderProjectionPort.update(order);

        return order;
    }
}
