package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.math.BigDecimal;

public record OrderLine(String name, Integer quantity, BigDecimal pricePerUnit, BigDecimal total) {

    public OrderLine(String name, Integer quantity, BigDecimal pricePerUnit) {
        this(name, quantity, pricePerUnit, BigDecimal.valueOf(pricePerUnit.doubleValue() * quantity));
    }
}
