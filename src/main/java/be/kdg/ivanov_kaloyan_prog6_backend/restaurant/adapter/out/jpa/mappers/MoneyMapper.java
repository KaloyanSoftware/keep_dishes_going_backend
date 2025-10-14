package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import org.mapstruct.Mapper;
import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface MoneyMapper {

    default Double toDouble(BigDecimal bd) { return bd.doubleValue(); }

    default BigDecimal toBigDecimal(Double d) { return BigDecimal.valueOf(d); }
}
