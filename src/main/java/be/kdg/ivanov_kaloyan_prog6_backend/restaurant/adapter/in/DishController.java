package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.DishDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@PreAuthorize("hasAuthority('owner')")
@RequestMapping("/api/restaurant/{restaurantId}/menu/dishes")
public class DishController {

    private static final Logger log = LoggerFactory.getLogger(DishController.class);

    private final PublishDishUseCase publishDishUseCase;

    private final PublishDishDraftUseCase publishDishDraftUseCase;

    private final UnpublishDishUseCase unpublishDishUseCase;

    private final MarkDishOutOfStockUseCase markDishOutOfStockUseCase;

    private final MarkDishBackInStockUseCase markDishBackInStockUseCase;

    private final GetDishesForRestaurantUseCase getDishesForRestaurantUseCase;



    public DishController(final PublishDishUseCase publishDishUseCase,
                          final PublishDishDraftUseCase publishDishDraftUseCase,
                          final UnpublishDishUseCase unpublishDishUseCase,
                          final MarkDishOutOfStockUseCase markDishOutOfStockUseCase,
                          final MarkDishBackInStockUseCase markDishBackInStockUseCase,
                          final GetDishesForRestaurantUseCase getDishesForRestaurantUseCase) {
        this.publishDishUseCase = publishDishUseCase;
        this.publishDishDraftUseCase = publishDishDraftUseCase;
        this.unpublishDishUseCase = unpublishDishUseCase;
        this.markDishOutOfStockUseCase = markDishOutOfStockUseCase;
        this.markDishBackInStockUseCase = markDishBackInStockUseCase;
        this.getDishesForRestaurantUseCase = getDishesForRestaurantUseCase;
    }

    @PatchMapping(":publish")
    public ResponseEntity<Void> publish(@RequestBody PublishDishRequest request){
        final PublishDishCommand command =  new PublishDishCommand(request.dishId(), request.menuId());

        this.publishDishUseCase.publish(command);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/unpublish")
    public ResponseEntity<Void> unpublish(@RequestBody UnpublishDishRequest request,
                                          @PathVariable String restaurantId){
        final UnpublishDishCommand command =  new UnpublishDishCommand(UUID.fromString(request.id()), UUID.fromString(restaurantId));

        this.unpublishDishUseCase.unpublish(command);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(":outOfStock")
    public ResponseEntity<Void> markOutOfStock(@RequestBody MarkDishOutOfStockRequest request){
        final MarkDishOutOfStockCommand command =  new MarkDishOutOfStockCommand(request.dishId(), request.menuId());

        this.markDishOutOfStockUseCase.markOutOfStock(command);

        return ResponseEntity.ok().build();
    }

    @PatchMapping(":backInStock")
    public ResponseEntity<Void> markBackInStock(@RequestBody MarkDishBackInStockRequest request){
        final MarkDishBackInStockCommand command =  new MarkDishBackInStockCommand(request.dishId(), request.menuId());

        this.markDishBackInStockUseCase.markBackInStock(command);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> publish(@RequestBody PublishDishDraftRequest request,
                                        @PathVariable String restaurantId){


        final PublishDishDraftCommand command =  new PublishDishDraftCommand(UUID.fromString(request.draftId()),
                UUID.fromString(restaurantId));

        this.publishDishDraftUseCase.publish(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<DishDTO>> getDishes(@PathVariable String restaurantId){
        final GetDishesForRestaurantCommand command = new GetDishesForRestaurantCommand(UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(this.getDishesForRestaurantUseCase.getDishes(command).stream()
                .map(DishDTO::from).toList());
    }

}
