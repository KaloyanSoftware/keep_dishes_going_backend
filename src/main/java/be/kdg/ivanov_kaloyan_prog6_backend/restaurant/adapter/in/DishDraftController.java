package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.DishDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.DishDraftDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.CreateDishAsDraftRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.PublishDishDraftRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.PublishDishRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateDishAsDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.PublishDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateDishAsDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.PublishDishDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.PublishDishUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drafts")
public class DishDraftController {
    private final CreateDishAsDraftUseCase createDishAsDraftUseCase;

    public DishDraftController(final CreateDishAsDraftUseCase createDishAsDraftUseCase) {
        this.createDishAsDraftUseCase = createDishAsDraftUseCase;
    }

    @PostMapping
    public ResponseEntity<DishDraftDTO> post(@RequestBody CreateDishAsDraftRequest request){

        final CreateDishAsDraftCommand command =  new CreateDishAsDraftCommand(
                request.restaurantId(), request.name(), request.type(), request.tags(),
                request.description(), request.price(), request.pictureURL()
        );

        DishDraft dishDraft = this.createDishAsDraftUseCase.createDishAsDraft(command);

        return  ResponseEntity.status(HttpStatus.CREATED).body(DishDraftDTO.from(dishDraft));
    }
}
