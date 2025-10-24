package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request.AddItemToBasketRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.BasketDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RemoveBasketItemCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ManageBasketItemsUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    private final ManageBasketItemsUseCase manageBasketItemsUseCase;

    public BasketController(ManageBasketItemsUseCase manageBasketItemsUseCase) {
        this.manageBasketItemsUseCase = manageBasketItemsUseCase;
    }

    @PostMapping("/basketLines")
    public ResponseEntity<BasketDTO> addItem(@RequestBody AddItemToBasketRequest request) {
        AddNewItemToBasketCommand command =
                new AddNewItemToBasketCommand(request.restaurantId(),
                        request.dishId(), request.customerSessionId());

        Basket basket = this.manageBasketItemsUseCase.add(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(BasketDTO.from(basket));
    }

    @DeleteMapping("/{basketId}/basketLines/{basketLineId}")
    public ResponseEntity<Void> removeItem(@PathVariable String basketId, @PathVariable String basketLineId) {
        RemoveBasketItemCommand command =
                new RemoveBasketItemCommand(UUID.fromString(basketId), UUID.fromString(basketLineId));

        this.manageBasketItemsUseCase.remove(command);

        return ResponseEntity.noContent().build();
    }

}
