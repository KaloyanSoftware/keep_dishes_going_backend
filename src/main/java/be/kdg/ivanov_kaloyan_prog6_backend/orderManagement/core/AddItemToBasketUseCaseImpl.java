package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.BasketNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.ItemNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.ItemResponseError;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Item;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.AddItemToBasketUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.PublishedDishCatalog;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.SaveBasketPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddItemToBasketUseCaseImpl implements AddItemToBasketUseCase {

    private final SaveBasketPort saveBasketPort;

    private final LoadBasketPort loadBasketPort;

    private final PublishedDishCatalog catalog;

    public AddItemToBasketUseCaseImpl(final SaveBasketPort saveBasketPort,
                                      final LoadBasketPort loadBasketPort,
                                      final PublishedDishCatalog catalog) {
        this.saveBasketPort = saveBasketPort;
        this.loadBasketPort = loadBasketPort;
        this.catalog = catalog;
    }

    @Override
    public Basket add(AddNewItemToBasketCommand command) {

        final Basket basket = loadBasketPort.loadBy(command.ownerId())
                .orElseGet(() -> new Basket(command.restaurantId(), command.ownerId()));

        var dish = catalog.findPublishedDish(command.restaurantId(), command.dishId())
                .orElseThrow(() -> new ItemNotFoundException("Dish not available"));

        Item item = new Item(dish.restaurantId(), dish.dishId(), dish.name(),
                dish.price(), 1,dish.pictureURL(), dish.outOfStock());


        basket.addItem(item);
        saveBasketPort.save(basket);

        return basket;
    }
}
