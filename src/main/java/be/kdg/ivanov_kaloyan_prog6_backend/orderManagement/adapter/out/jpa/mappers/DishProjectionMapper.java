package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.DishProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.FoodTagProjection;
import org.mapstruct.*;
import java.math.BigDecimal;

@Mapper(componentModel = "spring", imports = {DishProjection.class, FoodTagProjection.class})
public interface DishProjectionMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "restaurantId", source = "restaurantId")
    @Mapping(target = "stockStatus", source = "stockStatus")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", expression = "java(domain.getPrice().doubleValue())")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    @Mapping(target = "tags", source = "tags")
    DishProjectionJpaEntity toJpa(DishProjection domain);


    @ObjectFactory
    default DishProjection createDishProjection(DishProjectionJpaEntity jpa) {
        return DishProjection.create(
                jpa.getId(),
                jpa.getRestaurantId(),
                jpa.getStockStatus(),
                jpa.getName(),
                jpa.getType(),
                jpa.getTags(),
                jpa.getDescription(),
                jpa.getPrice() != null ? BigDecimal.valueOf(jpa.getPrice()) : BigDecimal.ZERO,
                jpa.getPictureUrl()
        );
    }

    default DishProjection toDomain(DishProjectionJpaEntity jpa) {
        return createDishProjection(jpa);
    }
}
