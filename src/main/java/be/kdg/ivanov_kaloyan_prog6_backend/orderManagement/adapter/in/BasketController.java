package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request.AddItemToBasketRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request.RemoveBasketItemRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.response.BasketDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RemoveBasketItemCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ManageBasketItemsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final ManageBasketItemsUseCase manageBasketItemsUseCase;

    public BasketController(ManageBasketItemsUseCase manageBasketItemsUseCase) {
        this.manageBasketItemsUseCase = manageBasketItemsUseCase;
    }

    @PostMapping("/items")
    public ResponseEntity<BasketDTO> addItem(@RequestBody AddItemToBasketRequest request) {
        AddNewItemToBasketCommand command =
                new AddNewItemToBasketCommand(request.restaurantId(),
                        request.dishId(), request.ownerId());

        Basket basket = this.manageBasketItemsUseCase.add(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(BasketDTO.from(basket));
    }

    @DeleteMapping("/items")
    public ResponseEntity<Void> removeItem(@RequestBody RemoveBasketItemRequest request) {
        RemoveBasketItemCommand command =
                new RemoveBasketItemCommand(request.basketId(), request.dishId());

        this.manageBasketItemsUseCase.remove(command);

        return ResponseEntity.noContent().build();
    }

}
