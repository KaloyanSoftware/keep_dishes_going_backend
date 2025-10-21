package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.AddressEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.DayScheduleEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.RestaurantJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.mapstruct.*;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {RestaurantId.class, OwnerId.class})
public interface RestaurantMapper {

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "ownerId", expression = "java(domain.getOwnerId().id())")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    @Mapping(target = "defaultPrepMinutes", source = "defaultPrepTime")
    @Mapping(target = "cuisineType", source = "cuisineType")
    @Mapping(target = "address", expression = "java(toJpaAddress(domain.getAddress()))")
    @Mapping(target = "openingHours", expression = "java(toJpaOpeningHours(domain.getOpeningHours()))")
    RestaurantJpaEntity toJpa(Restaurant domain);

    @ObjectFactory
    default Restaurant createRestaurant(RestaurantJpaEntity jpa) {
        return new Restaurant(
                RestaurantId.of(jpa.getId()),
                OwnerId.of(jpa.getOwnerId()),
                toDomainAddress(jpa.getAddress()),
                jpa.getEmail(),
                jpa.getPictureUrl(),
                jpa.getDefaultPrepMinutes(),
                jpa.getCuisineType(),
                toDomainOpeningHours(jpa.getOpeningHours())
        );
    }

    default Restaurant toDomain(RestaurantJpaEntity jpa) {
        return createRestaurant(jpa);
    }

    default AddressEmbeddable toJpaAddress(Address address) {
        return new AddressEmbeddable(
                address.street(),
                address.number(),
                address.postalCode(),
                address.city(),
                address.country()
        );
    }

    default Address toDomainAddress(AddressEmbeddable emb) {
        return new Address(
                emb.getStreet(),
                emb.getNumber(),
                emb.getPostalCode(),
                emb.getCity(),
                emb.getCountry()
        );
    }

    default Map<DayOfWeek, DayScheduleEmbeddable> toJpaOpeningHours(OpeningHours domain) {
        return domain.openingHours().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new DayScheduleEmbeddable(e.getValue().start(), e.getValue().end())
                ));
    }

    default OpeningHours toDomainOpeningHours(Map<DayOfWeek, DayScheduleEmbeddable> jpaMap) {
        Map<DayOfWeek, DaySchedule> domainMap = jpaMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new DaySchedule(e.getValue().getStart(), e.getValue().getEnd())
                ));
        return new OpeningHours(domainMap);
    }
}
