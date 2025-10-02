package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import org.springframework.util.Assert;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public record CreateDishAsDraftCommand(UUID restaurantId, String name, DishType type, Set<FoodTag> tags,
                                       String description, BigDecimal price, String pictureURL){

    public CreateDishAsDraftCommand{
        Assert.notNull(restaurantId, "restaurantId must not be null");
        Assert.notNull(type, "type must not be null");
        Assert.notNull(tags, "tags must not be null");
        Assert.notEmpty(tags, "tags must not be empty");
        Assert.notNull(description, "description must not be null");
        Assert.notNull(price, "price must not be null");
        Assert.isTrue(price.compareTo(BigDecimal.ZERO) > 0, "price must be greater than zero");
        Assert.notNull(pictureURL, "pictureURL must not be null");
    }
}


