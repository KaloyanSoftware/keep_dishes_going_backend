package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.*;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.BasketNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.RestaurantProjectionNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.CreateOrderCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.CreateOrderUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.DeleteBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadRestaurantProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateOrderPort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {
    private final LoadBasketPort loadBasketPort;

    private final List<UpdateOrderPort> updateOrderPorts;

    private final DeleteBasketPort deleteBasketPort;

    private final LoadRestaurantProjectionPort loadRestaurantProjectionPort;

    public CreateOrderUseCaseImpl(final LoadBasketPort loadBasketPort,
                                  final List<UpdateOrderPort> updateOrderPorts,
                                  final DeleteBasketPort deleteBasketPort,
                                  final LoadRestaurantProjectionPort loadRestaurantProjectionPort) {
        this.loadBasketPort = loadBasketPort;
        this.updateOrderPorts = updateOrderPorts;
        this.deleteBasketPort = deleteBasketPort;
        this.loadRestaurantProjectionPort = loadRestaurantProjectionPort;
    }

    @Override
    public Order create(CreateOrderCommand command) {
        final CustomerInfo customerInfo = toCustomerInfo(command);

        final Basket basket = loadBasketPort.loadByBasketId(command.basketId()).orElseThrow(
                () -> new BasketNotFoundException("Basket with id: " + command.basketId() + " not found!")
        );

        final RestaurantProjection projection = loadRestaurantProjectionPort.loadBy(basket.getRestaurantId()).orElseThrow(
                () -> new RestaurantProjectionNotFoundException("Restaurant projection with id: " + basket.getRestaurantId() + " not found!")
        );

        projection.checkOrderAvailability();

        final Order order = new Order(basket, customerInfo, command.customerSessionId());

        this.updateOrderPorts.forEach(updateOrderPort -> updateOrderPort.update(order));

        deleteBasketPort.delete(basket);
        order.commitEvents();

        return order;
    }

    private static CustomerInfo toCustomerInfo(CreateOrderCommand command) {
        final Location deliveryAddress = new Location(command.customerInfo().deliveryAddress().street(),
                                                      command.customerInfo().deliveryAddress().number(),
                                                      command.customerInfo().deliveryAddress().postalCode(),
                                                      command.customerInfo().deliveryAddress().city(),
                                                      command.customerInfo().deliveryAddress().country());

        final CustomerInfo customerInfo = new CustomerInfo(command.customerInfo().name(),
                                                           deliveryAddress,
                                                           command.customerInfo().email());
        return customerInfo;
    }
}
