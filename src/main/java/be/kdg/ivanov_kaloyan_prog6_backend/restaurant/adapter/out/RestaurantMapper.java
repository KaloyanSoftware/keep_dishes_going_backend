package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Address;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "id", source = "restaurantId.id")
    @Mapping(target = "ownerId", source = "ownerId.id")
    @Mapping(target = "address", expression = "java(toEmbeddable(domain.getAddress()))")
    RestaurantJpaEntity toJpa(Restaurant domain);

    @Mapping(target = "restaurantId", expression = "java(new RestaurantId(jpa.getId()))")
    @Mapping(target = "ownerId", expression = "java(new OwnerId(jpa.getOwnerId()))")
    @Mapping(target = "address", expression = "java(toDomainAddress(jpa.getAddress()))")
    Restaurant toDomain(RestaurantJpaEntity jpa);

    private AddressEmbeddable toEmbeddable(Address address) {
        return new AddressEmbeddable(
                address.street(),
                address.number(),
                address.postalCode(),
                address.city(),
                address.country()
        );
    }

    private Address toDomainAddress(AddressEmbeddable emb) {
        return new Address(
                emb.getStreet(),
                emb.getNumber(),
                emb.getPostalCode(),
                emb.getCity(),
                emb.getCountry()
        );
    }
}
