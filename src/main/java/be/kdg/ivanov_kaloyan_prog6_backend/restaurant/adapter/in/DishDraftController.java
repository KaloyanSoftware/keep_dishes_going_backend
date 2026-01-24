package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.DishDraftDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.CreateDishDraftRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateDishDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.DeleteDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetDishDraftsForRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateDishDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.DeleteDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetDishDraftsByRestaurantUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DishDraftController {
    private final CreateDishDraftUseCase createDishDraftUseCase;

    private final GetDishDraftsByRestaurantUseCase getDishDraftsByRestaurantUseCase;

    private final DeleteDraftUseCase deleteDraftUseCase;

    public DishDraftController(final CreateDishDraftUseCase createDishDraftUseCase,
                               final GetDishDraftsByRestaurantUseCase getDishDraftsByRestaurantUseCase,
                               final DeleteDraftUseCase deleteDraftUseCase) {
        this.createDishDraftUseCase = createDishDraftUseCase;
        this.getDishDraftsByRestaurantUseCase = getDishDraftsByRestaurantUseCase;
        this.deleteDraftUseCase = deleteDraftUseCase;
    }

    @PostMapping("/drafts")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<DishDraftDTO> post(@RequestBody CreateDishDraftRequest request){

        final CreateDishDraftCommand command =  new CreateDishDraftCommand(
                UUID.fromString(request.restaurantId()), request.dishId(),request.name(), request.type(),
                Arrays.stream(request.foodTags()).map(FoodTag::valueOf).toList(),
                request.description(), request.price(), request.pictureURL()
        );

        return  ResponseEntity.status(HttpStatus.CREATED).body(DishDraftDTO.from(this.createDishDraftUseCase.create(command)));
    }

    @GetMapping("/owner/restaurant/{restaurantId}/drafts")
    public ResponseEntity<List<DishDraftDTO>> getDrafts(@PathVariable String restaurantId){

        final GetDishDraftsForRestaurantCommand command = new GetDishDraftsForRestaurantCommand(UUID.fromString(restaurantId));

        final List<DishDraftDTO> drafts = getDishDraftsByRestaurantUseCase.getDishDrafts(command)
                .stream().map(DishDraftDTO::from).toList();

        return ResponseEntity.ok().body(drafts);
    }

    @DeleteMapping("/drafts/{id}")
    public ResponseEntity<Void> deleteDraft(@PathVariable String id){
        final DeleteDraftCommand command = new DeleteDraftCommand(id);

        this.deleteDraftUseCase.deleteDraft(command);

        return ResponseEntity.noContent().build();
    }
}
