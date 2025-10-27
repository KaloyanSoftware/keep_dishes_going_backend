package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.OrderProjectionDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.ChangeOrderStatusRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.ChangeOrderProjectionStatusCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.ChangeOrderProjectionStatusUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/owner/orders/{orderId}")
public class OrderProjectionController {

    private final ChangeOrderProjectionStatusUseCase changeOrderProjectionStatusUseCase;

    public OrderProjectionController(final ChangeOrderProjectionStatusUseCase changeOrderProjectionStatusUseCase) {
        this.changeOrderProjectionStatusUseCase = changeOrderProjectionStatusUseCase;
    }

    @PatchMapping("/status")
    public ResponseEntity<OrderProjectionDTO> patch(@PathVariable String orderId,
                                                    @RequestBody ChangeOrderStatusRequest request){
        final ChangeOrderProjectionStatusCommand command = new ChangeOrderProjectionStatusCommand(UUID.fromString(orderId), request.status());

        return ResponseEntity.ok().body(OrderProjectionDTO.from(changeOrderProjectionStatusUseCase.changeStatus(command)));
    }
}
