package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.request.PublishDishRequest;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.PublishDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.PublishDishUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final PublishDishUseCase publishDishUseCase;

    public DishController(PublishDishUseCase publishDishUseCase) {
        this.publishDishUseCase = publishDishUseCase;
    }

    @PatchMapping
    public ResponseEntity<Void> publish(@RequestBody PublishDishRequest request){
        final PublishDishCommand command =  new PublishDishCommand(request.dishId(), request.menuId());

        this.publishDishUseCase.publish(command);

        return ResponseEntity.ok().build();
    }

}
