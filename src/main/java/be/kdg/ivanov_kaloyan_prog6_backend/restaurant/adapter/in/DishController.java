package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.DishDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final PublishDishUseCase publishDishUseCase;

    private final PublishDishDraftUseCase publishDishDraftUseCase;

    private final UnpublishDishUseCase unpublishDishUseCase;

    private final MarkDishOutOfStockUseCase markDishOutOfStockUseCase;

    private final MarkDishBackInStockUseCase markDishBackInStockUseCase;

    public DishController(final PublishDishUseCase publishDishUseCase,
                          final PublishDishDraftUseCase publishDishDraftUseCase,
                          final UnpublishDishUseCase unpublishDishUseCase,
                          final MarkDishOutOfStockUseCase markDishOutOfStockUseCase,
                          final MarkDishBackInStockUseCase markDishBackInStockUseCase) {
        this.publishDishUseCase = publishDishUseCase;
        this.publishDishDraftUseCase = publishDishDraftUseCase;
        this.unpublishDishUseCase = unpublishDishUseCase;
        this.markDishOutOfStockUseCase = markDishOutOfStockUseCase;
        this.markDishBackInStockUseCase = markDishBackInStockUseCase;
    }

    @PatchMapping(":publish")
    public ResponseEntity<DishDTO> publish(@RequestBody PublishDishRequest request){
        final PublishDishCommand command =  new PublishDishCommand(request.dishId(), request.menuId());

        return ResponseEntity.ok().body(DishDTO.from(this.publishDishUseCase.publish(command)));
    }

    @PatchMapping(":unpublish")
    public ResponseEntity<DishDTO> unpublish(@RequestBody UnpublishDishRequest request){
        final UnpublishDishCommand command =  new UnpublishDishCommand(request.dishId(), request.menuId());

        return ResponseEntity.ok().body(DishDTO.from(this.unpublishDishUseCase.unpublish(command)));
    }

    @PatchMapping(":outOfStock")
    public ResponseEntity<DishDTO> markOutOfStock(@RequestBody MarkDishOutOfStockRequest request){
        final MarkDishOutOfStockCommand command =  new MarkDishOutOfStockCommand(request.dishId(), request.menuId());

        return ResponseEntity.ok().body(DishDTO.from(this.markDishOutOfStockUseCase.markOutOfStock(command)));
    }

    @PatchMapping(":backInStock")
    public ResponseEntity<DishDTO> markBackInStock(@RequestBody MarkDishBackInStockRequest request){
        final MarkDishBackInStockCommand command =  new MarkDishBackInStockCommand(request.dishId(), request.menuId());

        return ResponseEntity.ok().body(DishDTO.from(this.markDishBackInStockUseCase.markBackInStock(command)));
    }

    //publish dish draft = put a dish on the menu that has been a draft until now
    //or publish a change for a dish that's currently not on the menu
    @PostMapping
    public ResponseEntity<DishDTO> publish(@RequestBody PublishDishDraftRequest request){
        final PublishDishDraftCommand command =  new PublishDishDraftCommand(request.draftId(), request.restaurantId());

        return ResponseEntity.status(HttpStatus.CREATED).body(DishDTO.from(this.publishDishDraftUseCase.publish(command)));
    }





}
