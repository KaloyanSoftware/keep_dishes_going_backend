package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request.AddItemToBasketRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.BasketDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Basket;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.AddNewItemToBasketCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.RemoveBasketItemCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.ManageBasketItemsUseCase;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer/baskets")
public class BasketController {

    private final ManageBasketItemsUseCase manageBasketItemsUseCase;

    public BasketController(ManageBasketItemsUseCase manageBasketItemsUseCase) {
        this.manageBasketItemsUseCase = manageBasketItemsUseCase;
    }

    @PostMapping("/basketLines")
    public ResponseEntity<BasketDTO> addItem(@RequestBody AddItemToBasketRequest request,
                                             @CookieValue(name = "customerSessionId", required = false) String sessionId,
                                             HttpServletResponse response) {

        AddNewItemToBasketCommand command =
                new AddNewItemToBasketCommand(request.restaurantId(),
                        request.dishId(), setCookie(sessionId, response));

        Basket basket = this.manageBasketItemsUseCase.add(command);

        return ResponseEntity.status(HttpStatus.CREATED).body(BasketDTO.from(basket));
    }

    @PatchMapping("/{basketId}/basketLines/{dishId}")
    public ResponseEntity<BasketDTO> lowerLineQuantity(@PathVariable String basketId, @PathVariable String dishId) {
        RemoveBasketItemCommand command =
                new RemoveBasketItemCommand(UUID.fromString(basketId), UUID.fromString(dishId));

        Basket basket = this.manageBasketItemsUseCase.lowerQuantity(command);

        if(basket == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(BasketDTO.from(basket));
    }

    private String setCookie(String sessionId, HttpServletResponse response){
        if (sessionId == null || sessionId.isBlank()) {
            sessionId = UUID.randomUUID().toString();

            ResponseCookie cookie = ResponseCookie.from("customerSessionId", sessionId)
                    .path("/")
                    .httpOnly(true)
                    .secure(false)
                    .sameSite("Lax")
                    .maxAge(60 * 60 * 24 * 7)
                    .build();

            response.addHeader("Set-Cookie", cookie.toString());
        }

        return sessionId;
    }

}
