package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.BasketNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.DishProjectionNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RemoveBasketItemCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ManageBasketItemsUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.DeleteBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadDishProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateBasketPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ManageBasketItemsItemsUseCaseImpl implements ManageBasketItemsUseCase {

    private static final Logger log = LoggerFactory.getLogger(ManageBasketItemsItemsUseCaseImpl.class);
    private final UpdateBasketPort updateBasketPort;

    private final LoadBasketPort loadBasketPort;

    private final LoadDishProjectionPort loadDishProjectionPort;

    private final DeleteBasketPort deleteBasketPort;



    public ManageBasketItemsItemsUseCaseImpl(final UpdateBasketPort updateBasketPort,
                                             final LoadBasketPort loadBasketPort,
                                             final LoadDishProjectionPort loadDishProjectionPort,
                                             final DeleteBasketPort deleteBasketPort) {
        this.updateBasketPort = updateBasketPort;
        this.loadBasketPort = loadBasketPort;
        this.loadDishProjectionPort = loadDishProjectionPort;
        this.deleteBasketPort = deleteBasketPort;
    }

    @Override
    public Basket add(AddNewItemToBasketCommand command) {

        final Basket basket = loadBasketPort.loadBy(UUID.fromString(command.customerSessionId()))
                .orElseGet(() -> new Basket(command.restaurantId(), UUID.fromString(command.customerSessionId())));

        final DishProjection projection = loadDishProjectionPort.loadBy(command.dishId()).orElseThrow(
                () -> new DishProjectionNotFoundException("Dish projection with id: " + command.dishId() + " not found!")
        );

        basket.addLine(command.restaurantId(), command.dishId(), projection.getName(),
                projection.getPrice(), projection.getPictureURL());

        log.error(basket.toString());

        return updateBasketPort.update(basket);
    }

    @Override
    public Basket lowerQuantity(RemoveBasketItemCommand command) {
        final Basket basket = loadBasketPort.loadByBasketId(command.basketId()).orElseThrow(
                () -> new BasketNotFoundException("Can't find basket with id: " + command.basketId())
        );

        basket.decreaseLineQuantity(command.dishId());

        if(basket.empty()){
            deleteBasketPort.delete(basket);
            return null;
        }

        return updateBasketPort.update(basket);
    }


}
