package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;

public interface AddItemToBasketUseCase {
    Basket add(AddNewItemToBasketCommand command);
}
