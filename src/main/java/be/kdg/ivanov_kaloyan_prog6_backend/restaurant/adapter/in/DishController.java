package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.CreateDishAsDraftRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateDishAsDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateDishAsDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.EditDishAsDraftUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/dishes")
public class DishController {
    private final CreateDishAsDraftUseCase createDishAsDraftUseCase;

    private final EditDishAsDraftUseCase editDishAsDraftUseCase;

    public DishController(CreateDishAsDraftUseCase createDishAsDraftUseCase, EditDishAsDraftUseCase editDishAsDraftUseCase) {
        this.createDishAsDraftUseCase = createDishAsDraftUseCase;
        this.editDishAsDraftUseCase = editDishAsDraftUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateDishAsDraftRequest request,
                                       @PathVariable UUID restaurantId){

        final CreateDishAsDraftCommand command =  new CreateDishAsDraftCommand(
                restaurantId, request.name(), request.type(), request.tags(),
                request.description(), request.price(), request.pictureURL()
        );

        this.createDishAsDraftUseCase.createDishAsDraft(command);

        return  ResponseEntity.ok().build();
    }

}
