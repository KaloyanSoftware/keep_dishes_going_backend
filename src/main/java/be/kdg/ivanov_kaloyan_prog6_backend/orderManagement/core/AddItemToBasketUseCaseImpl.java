package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.exceptions.BasketNotFoundException;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Item;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.AddItemToBasketUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadBasketPort;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.SaveBasketPort;
import org.springframework.stereotype.Service;

@Service
public class AddItemToBasketUseCaseImpl implements AddItemToBasketUseCase {

    private final SaveBasketPort saveBasketPort;

    private final LoadBasketPort loadBasketPort;

    public AddItemToBasketUseCaseImpl(SaveBasketPort saveBasketPort, LoadBasketPort loadBasketPort) {
        this.saveBasketPort = saveBasketPort;
        this.loadBasketPort = loadBasketPort;
    }

    @Override
    public Basket add(AddNewItemToBasketCommand command) {
        Basket basket;
        if(command.basketId() == null){
            basket = new Basket(command.restaurantId());
        }else{
            basket = loadBasketPort.loadBy(command.basketId()).orElseThrow(
                    () -> new BasketNotFoundException("Can't find basket with id: " + command.basketId()));
        }
        Item item;

        //TODO:
        //send a command to restaurant BC to get the dish
        //or add a request body for item dto in request (less likely)

        //TODO:
        //increase dish quantity if item is added to the basket a second time

        //basket.addItem(item);
        saveBasketPort.save(basket);

        return basket;
    }
}
