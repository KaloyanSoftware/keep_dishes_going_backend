package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.DishDraftDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.CreateDishDraftRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateDishDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetDishDraftsForRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateDishDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetDishDraftsByRestaurantUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class DishDraftController {
    private static final Logger log = LoggerFactory.getLogger(DishDraftController.class);
    private final CreateDishDraftUseCase createDishDraftUseCase;

    private final GetDishDraftsByRestaurantUseCase getDishDraftsByRestaurantUseCase;



    public DishDraftController(final CreateDishDraftUseCase createDishDraftUseCase,
                               final GetDishDraftsByRestaurantUseCase getDishDraftsByRestaurantUseCase) {
        this.createDishDraftUseCase = createDishDraftUseCase;
        this.getDishDraftsByRestaurantUseCase = getDishDraftsByRestaurantUseCase;
    }

    @PostMapping("/drafts")
    public ResponseEntity<DishDraftDTO> post(@RequestBody CreateDishDraftRequest request){

        final CreateDishDraftCommand command =  new CreateDishDraftCommand(
                request.restaurantId(), request.dishId(),request.name(), request.type(),
                Arrays.stream(request.foodTags()).map(FoodTag::valueOf).toList(),
                request.description(), request.price(), request.pictureURL()
        );

        return  ResponseEntity.status(HttpStatus.CREATED).body(DishDraftDTO.from(this.createDishDraftUseCase.create(command)));
    }

    @GetMapping("/owner/restaurant/{restaurantId}/drafts")
    public ResponseEntity<List<DishDraftDTO>> getDrafts(@PathVariable UUID restaurantId){

        log.error(restaurantId.toString());

        final GetDishDraftsForRestaurantCommand command = new GetDishDraftsForRestaurantCommand(restaurantId);

        final List<DishDraftDTO> drafts = getDishDraftsByRestaurantUseCase.getDishDrafts(command)
                .stream().map(DishDraftDTO::from).toList();

        return ResponseEntity.ok().body(drafts);
    }
}
