package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.CustomerInfoEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.LocationEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.OrderLineEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.OrderJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", expression = "java(order.getId().orderId())")
    @Mapping(target = "orderLines", expression = "java(toEmbeddableOrderLines(order.getOrderLines()))")
    @Mapping(target = "customerInfo", expression = "java(toEmbeddableCustomerInfo(order.getCustomerInfo()))")
    @Mapping(target = "total", source = "total")
    @Mapping(target = "customerSessionId", expression = "java(order.getCustomerSessionId())")
    OrderJpaEntity toJpa(Order order);

    @ObjectFactory
    default Order createOrder(OrderJpaEntity jpa) {
        return new Order(
                OrderId.of(jpa.getId()),
                toDomainOrderLines(jpa.getOrderLines()),
                toDomainCustomerInfo(jpa.getCustomerInfo()),
                jpa.getTotal() != null ? jpa.getTotal() : BigDecimal.ZERO,
                jpa.getCustomerSessionId()
        );
    }

    default Order toDomain(OrderJpaEntity jpa) {
        return createOrder(jpa);
    }

    default List<OrderLine> toDomainOrderLines(List<OrderLineEmbeddable> embeddables) {
        if (embeddables == null) return List.of();
        return embeddables.stream()
                .map(e -> new OrderLine(e.getName(), e.getQuantity(), e.getPricePerUnit(), e.getTotal()))
                .collect(Collectors.toList());
    }

    default List<OrderLineEmbeddable> toEmbeddableOrderLines(List<OrderLine> lines) {
        if (lines == null) return List.of();
        return lines.stream()
                .map(l -> new OrderLineEmbeddable(l.name(), l.quantity(), l.pricePerUnit(), l.total()))
                .collect(Collectors.toList());
    }

    default CustomerInfo toDomainCustomerInfo(CustomerInfoEmbeddable embeddable) {
        if (embeddable == null) return null;
        return new CustomerInfo(
                embeddable.getName(),
                toDomainLocation(embeddable.getDeliveryAddress()),
                embeddable.getEmail()
        );
    }

    default CustomerInfoEmbeddable toEmbeddableCustomerInfo(CustomerInfo info) {
        if (info == null) return null;
        return new CustomerInfoEmbeddable(
                info.name(),
                toEmbeddableLocation(info.deliveryAddress()),
                info.email()
        );
    }

    default Location toDomainLocation(LocationEmbeddable embeddable) {
        if (embeddable == null) return null;
        return new Location(
                embeddable.getStreet(),
                embeddable.getNumber(),
                embeddable.getPostalCode(),
                embeddable.getCity(),
                embeddable.getCountry()
        );
    }

    default LocationEmbeddable toEmbeddableLocation(Location location) {
        if (location == null) return null;
        return new LocationEmbeddable(
                location.street(),
                location.number(),
                location.postalCode(),
                location.city(),
                location.country()
        );
    }
}
