package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.CustomerInfo;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Location;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Order;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.BasketNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.CreateOrderCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.CreateOrderUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.DeleteBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateOrderPort;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {
    private final LoadBasketPort loadBasketPort;

    private final UpdateOrderPort updateOrderPort;

    private final DeleteBasketPort deleteBasketPort;

    public CreateOrderUseCaseImpl(final LoadBasketPort loadBasketPort,
                                  final UpdateOrderPort updateOrderPort,
                                  final DeleteBasketPort deleteBasketPort) {
        this.loadBasketPort = loadBasketPort;
        this.updateOrderPort = updateOrderPort;
        this.deleteBasketPort = deleteBasketPort;
    }

    @Override
    public Order create(CreateOrderCommand command) {
        final Location deliveryAddress = new Location(command.customerInfo().deliveryAddress().street(),
                                                      command.customerInfo().deliveryAddress().number(),
                                                      command.customerInfo().deliveryAddress().postalCode(),
                                                      command.customerInfo().deliveryAddress().city(),
                                                      command.customerInfo().deliveryAddress().country());

        final CustomerInfo customerInfo = new CustomerInfo(command.customerInfo().name(),
                                                           deliveryAddress,
                                                           command.customerInfo().email());
        final Basket basket = loadBasketPort.loadByBasketId(command.basketId()).orElseThrow(
                () -> new BasketNotFoundException("Basket with id: " + command.basketId() + " not found!")
        );

        final Order order = new Order(basket, customerInfo, command.customerSessionId());

        final Order saved = updateOrderPort.update(order);

        deleteBasketPort.delete(basket);

        return saved;
    }
}
