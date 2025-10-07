package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.MenuId;
import org.mapstruct.Mapper;
import java.math.BigDecimal;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface IdMoneyMapper {

    default UUID toUUID(MenuId id) { return id.id();}

    default UUID toUUID(DishId id) { return id.id(); }

    default Double toDouble(BigDecimal bd) { return bd.doubleValue(); }

    default BigDecimal toBigDecimal(Double d) { return BigDecimal.valueOf(d); }
}
