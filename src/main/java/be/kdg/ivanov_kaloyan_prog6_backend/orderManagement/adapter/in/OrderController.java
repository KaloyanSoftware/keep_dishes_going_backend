package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.OrderDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request.CreateOrderRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.CreateOrderCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.CreateOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer/baskets/{basketId}/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(final CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@PathVariable String basketId,
                                           @RequestBody CreateOrderRequest request,
                                           @CookieValue(name = "customerSessionId", required = false) String sessionId){
        final CreateOrderCommand command = new CreateOrderCommand(
                UUID.fromString(basketId),
                request.customerInfo(),
                UUID.fromString(sessionId));

        return ResponseEntity.status(HttpStatus.CREATED).body(OrderDTO.from(this.createOrderUseCase.create(command)));
    }
}
