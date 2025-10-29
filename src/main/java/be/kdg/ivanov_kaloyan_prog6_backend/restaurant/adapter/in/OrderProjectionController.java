package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.OrderProjectionDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.ChangeOrderStatusRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.ChangeOrderProjectionStatusCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetActiveOrdersCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.ChangeOrderProjectionStatusUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetActiveOrdersUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/owner")
public class OrderProjectionController {

    private final ChangeOrderProjectionStatusUseCase changeOrderProjectionStatusUseCase;

    private final GetActiveOrdersUseCase getActiveOrdersUseCase;

    public OrderProjectionController(final ChangeOrderProjectionStatusUseCase changeOrderProjectionStatusUseCase,
                                     final GetActiveOrdersUseCase getActiveOrdersUseCase) {
        this.changeOrderProjectionStatusUseCase = changeOrderProjectionStatusUseCase;
        this.getActiveOrdersUseCase = getActiveOrdersUseCase;
    }

    @PatchMapping("/orders/{orderId}")
    public ResponseEntity<OrderProjectionDTO> changeStatus(@PathVariable String orderId,
                                                    @RequestBody ChangeOrderStatusRequest request){
        final ChangeOrderProjectionStatusCommand command = new ChangeOrderProjectionStatusCommand(UUID.fromString(orderId), request.status());

        return ResponseEntity.ok().body(OrderProjectionDTO.from(changeOrderProjectionStatusUseCase.changeStatus(command)));
    }

    @GetMapping("/restaurant/{restaurantId}/activeOrders")
    public ResponseEntity<List<OrderProjectionDTO>> getActiveOrders(@PathVariable String restaurantId){
        final GetActiveOrdersCommand command = new GetActiveOrdersCommand(UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(getActiveOrdersUseCase.getActiveOrders(command).
                stream().map(OrderProjectionDTO::from).toList());
    }
}
