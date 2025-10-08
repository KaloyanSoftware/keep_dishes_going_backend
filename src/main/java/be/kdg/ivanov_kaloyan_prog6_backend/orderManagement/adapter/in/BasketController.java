package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request.AddItemToBasketRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.response.BasketDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.AddItemToBasketUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    private final AddItemToBasketUseCase addItemToBasketUseCase;

    public BasketController(AddItemToBasketUseCase addItemToBasketUseCase) {
        this.addItemToBasketUseCase = addItemToBasketUseCase;
    }

    @PostMapping("/items")
    public ResponseEntity<BasketDTO> addItem(@RequestBody AddItemToBasketRequest request) {
        AddNewItemToBasketCommand command =
                new AddNewItemToBasketCommand(request.restaurantId(),
                        request.dishId(), request.basketId());

        Basket basket = this.addItemToBasketUseCase.add(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(BasketDTO.from(basket));
    }
}
