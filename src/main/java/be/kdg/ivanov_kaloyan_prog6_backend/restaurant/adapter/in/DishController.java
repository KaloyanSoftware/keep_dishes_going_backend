package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.DishDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/restaurant/{restaurantId}/menu/dishes")
public class DishController {

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

    @PatchMapping("/published")
    public ResponseEntity<DishDTO> publish(@RequestBody PublishDishRequest request,
                                           @PathVariable String restaurantId){
        final PublishDishCommand command =  new PublishDishCommand(UUID.fromString(request.id()), UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(DishDTO.from(this.publishDishUseCase.publish(command)));
    }

    @PatchMapping("/unpublished")
    public ResponseEntity<DishDTO> unpublish(@RequestBody UnpublishDishRequest request,
                                             @PathVariable String restaurantId){

        final UnpublishDishCommand command =  new UnpublishDishCommand(UUID.fromString(request.id()), UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(DishDTO.from(this.unpublishDishUseCase.unpublish(command)));
    }

    @PatchMapping("/outOfStock")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<DishDTO> markOutOfStock(@RequestBody MarkDishOutOfStockRequest request,
                                               @PathVariable String restaurantId){
        final MarkDishOutOfStockCommand command =  new MarkDishOutOfStockCommand(UUID.fromString(request.dishId()),
                UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(DishDTO.from(this.markDishOutOfStockUseCase.markOutOfStock(command)));
    }

    @PatchMapping("/backInStock")
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<DishDTO> markBackInStock(@RequestBody MarkDishBackInStockRequest request,
                                                @PathVariable String restaurantId){
        final MarkDishBackInStockCommand command =  new MarkDishBackInStockCommand(UUID.fromString(request.dishId()),
                UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(DishDTO.from(this.markDishBackInStockUseCase.markBackInStock(command)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('owner')")
    public ResponseEntity<DishDTO> publish(@RequestBody PublishDishDraftRequest request,
                                        @PathVariable String restaurantId){

        final PublishDishDraftCommand command =  new PublishDishDraftCommand(UUID.fromString(request.draftId()),
                UUID.fromString(restaurantId));

        return ResponseEntity.status(HttpStatus.CREATED).body(DishDTO.from(this.publishDishDraftUseCase.publish(command)));
    }

    @GetMapping
    public ResponseEntity<List<DishDTO>> getDishes(@PathVariable String restaurantId){
        final GetDishesForRestaurantCommand command = new GetDishesForRestaurantCommand(UUID.fromString(restaurantId));

        return ResponseEntity.ok().body(this.getDishesForRestaurantUseCase.getDishes(command).stream()
                .map(DishDTO::from).toList());
    }

    @DeleteMapping("{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable String dishId,
                                           @PathVariable String restaurantId){


    }

}
