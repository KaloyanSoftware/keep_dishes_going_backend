package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class OrderLineEmbeddable {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price_per_unit", nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerUnit;

    @Column(name = "line_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    public OrderLineEmbeddable() {
    }

    public OrderLineEmbeddable(String name, Integer quantity, BigDecimal pricePerUnit, BigDecimal total) {
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
