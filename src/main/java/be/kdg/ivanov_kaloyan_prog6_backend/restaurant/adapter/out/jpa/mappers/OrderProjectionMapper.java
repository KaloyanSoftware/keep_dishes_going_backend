package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.DeliveryInfoEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.OrderProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DeliveryInfo;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderProjectionMapper {

    @Mappings({
            @Mapping(target = "id", source = "orderId"),
            @Mapping(target = "restaurantId", source = "restaurantId"),
            @Mapping(target = "orderLines", source = "orderLines"),
            @Mapping(target = "status", source = "status"),
            @Mapping(target = "deliveryInfo", expression = "java(toEmbeddableDeliveryInfo(domain.getDeliveryInfo()))")
    })
    OrderProjectionJpaEntity toJpa(OrderProjection domain);

    @ObjectFactory
    default OrderProjection rehydrate(OrderProjectionJpaEntity jpa) {
        return OrderProjection.rehydrate(
                jpa.getId(),
                jpa.getRestaurantId(),
                jpa.getOrderLines(),
                jpa.getStatus(),
                toDomainDeliveryInfo(jpa.getDeliveryInfo())
        );
    }

    default OrderProjection toDomain(OrderProjectionJpaEntity jpa) {
        return rehydrate(jpa);
    }

    default DeliveryInfo toDomainDeliveryInfo(DeliveryInfoEmbeddable embeddable) {
        if (embeddable == null) return null;
        return new DeliveryInfo(
                embeddable.getStreet(),
                embeddable.getNumber(),
                embeddable.getPostalCode(),
                embeddable.getCity()
        );
    }

    default DeliveryInfoEmbeddable toEmbeddableDeliveryInfo(DeliveryInfo info) {
        if (info == null) return null;
        return new DeliveryInfoEmbeddable(
                info.street(),
                info.number(),
                info.postalCode(),
                info.city()
        );
    }
}
