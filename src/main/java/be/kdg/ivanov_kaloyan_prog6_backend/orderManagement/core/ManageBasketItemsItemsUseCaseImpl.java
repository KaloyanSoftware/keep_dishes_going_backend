package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.BasketLine;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.BasketNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.DishProjectionNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RemoveBasketItemCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ManageBasketItemsUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadDishProjectionPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateBasketPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ManageBasketItemsItemsUseCaseImpl implements ManageBasketItemsUseCase {

    private final UpdateBasketPort updateBasketPort;

    private final LoadBasketPort loadBasketPort;

    private final LoadDishProjectionPort loadDishProjectionPort;

    public ManageBasketItemsItemsUseCaseImpl(final UpdateBasketPort updateBasketPort,
                                             final LoadBasketPort loadBasketPort,
                                             final LoadDishProjectionPort loadDishProjectionPort) {
        this.updateBasketPort = updateBasketPort;
        this.loadBasketPort = loadBasketPort;
        this.loadDishProjectionPort = loadDishProjectionPort;
    }

    @Override
    public Basket add(AddNewItemToBasketCommand command) {

        final Basket basket = loadBasketPort.loadBy(command.customerSessionId())
                .orElseGet(() -> new Basket(command.restaurantId(), command.customerSessionId()));

        final DishProjection projection = loadDishProjectionPort.loadBy(command.dishId()).orElseThrow(
                () -> new DishProjectionNotFoundException("Dish projection with id: " + command.dishId() + " not found!")
        );

        final BasketLine basketLine = new BasketLine(command.restaurantId(), command.dishId(), projection.getName(),
                projection.getPrice(), projection.getPictureURL());

        basket.addLine(basketLine);

        return updateBasketPort.save(basket);
    }

    @Override
    public void remove(RemoveBasketItemCommand command) {
        final Basket basket = loadBasketPort.loadByBasketId(command.basketId()).orElseThrow(
                () -> new BasketNotFoundException("Can't find basket with id: " + command.basketId())
        );

        basket.removeLine(command.dishId());
        updateBasketPort.save(basket);
    }


}
