package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto.OrderDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.request.CreateOrderRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.CreateOrderCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.GetOrderCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.CreateOrderUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.GetOrderUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;

    private final GetOrderUseCase getOrderUseCase;

    public OrderController(final CreateOrderUseCase createOrderUseCase,
                           final GetOrderUseCase getOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
    }

    @PostMapping("/baskets/{basketId}/orders")
    public ResponseEntity<OrderDTO> create(@PathVariable String basketId,
                                           @RequestBody CreateOrderRequest request,
                                           @CookieValue(name = "customerSessionId", required = false) String sessionId){
        final CreateOrderCommand command = new CreateOrderCommand(
                UUID.fromString(basketId),
                request.customerInfo(),
                UUID.fromString(sessionId));

        return ResponseEntity.status(HttpStatus.CREATED).body(OrderDTO.from(this.createOrderUseCase.create(command)));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDTO> get(@PathVariable String orderId){
        final GetOrderCommand command = new GetOrderCommand(UUID.fromString(orderId));

        return ResponseEntity.ok().body(OrderDTO.from(getOrderUseCase.getBy(command)));
    }
}
