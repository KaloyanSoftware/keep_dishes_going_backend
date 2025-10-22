package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.BasketNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.ItemNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Item;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RemoveBasketItemCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ManageBasketItemsUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.UpdateBasketPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ManageBasketItemsItemsUseCaseImpl implements ManageBasketItemsUseCase {

    private final UpdateBasketPort updateBasketPort;

    private final LoadBasketPort loadBasketPort;

    public ManageBasketItemsItemsUseCaseImpl(final UpdateBasketPort updateBasketPort,
                                             final LoadBasketPort loadBasketPort) {
        this.updateBasketPort = updateBasketPort;
        this.loadBasketPort = loadBasketPort;
    }

    @Override
    public Basket add(AddNewItemToBasketCommand command) {

        /*final Basket basket = loadBasketPort.loadBy(command.customerSessionId())
                .orElseGet(() -> new Basket(command.restaurantId(), command.customerSessionId()));

        //not like this, used the saved projection!
        var dish = catalog.findPublishedDish(command.restaurantId(), command.dishId())
                .orElseThrow(() -> new ItemNotFoundException("Dish not available"));

        Item item = new Item(dish.restaurantId(), dish.dishId(), dish.name(),
                dish.price(), 1,dish.pictureURL());


        basket.addItem(item);
        updateBasketPort.save(basket);*/

        return null;
    }

    @Override
    public void remove(RemoveBasketItemCommand command) {
        final Basket basket = loadBasketPort.loadByBasketId(command.basketId()).orElseThrow(
                () -> new BasketNotFoundException("Can't find basket with id: " + command.basketId())
        );

        basket.removeItem(command.dishId());
        updateBasketPort.save(basket);
    }


}
