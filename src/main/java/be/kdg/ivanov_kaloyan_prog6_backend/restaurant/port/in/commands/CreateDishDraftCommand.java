package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import org.springframework.util.Assert;
import java.util.List;
import java.util.UUID;

public record CreateDishDraftCommand(UUID restaurantId, UUID dishId,String name, DishType type, List<FoodTag> tags,
                                     String description, Double price, String pictureURL){

    public CreateDishDraftCommand {
        Assert.notNull(restaurantId, "restaurantId must not be null");
    }
}


