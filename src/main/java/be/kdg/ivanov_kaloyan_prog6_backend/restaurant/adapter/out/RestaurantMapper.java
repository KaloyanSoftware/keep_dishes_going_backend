package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = { RestaurantId.class, OwnerId.class })
public interface RestaurantMapper {

    // Domain -> JPA (owner is set in adapter; ignore here)
    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "email", expression = "java(domain.getEmail())")
    @Mapping(target = "pictureUrl", expression = "java(domain.getPictureURL())")
    @Mapping(target = "defaultPrepMinutes", expression = "java(domain.getDefaultPrepTime())") // adjust if you already use int
    @Mapping(target = "cuisineType", expression = "java(domain.getCuisineType())")
    @Mapping(target = "address", expression = "java(toJpaAddress(domain.getAddress()))")
    @Mapping(target = "openingHours", expression = "java(toJpaOpeningHours(domain.getOpeningHours()))")
    RestaurantJpaEntity toJpa(Restaurant domain);

    // JPA -> Domain
    @Mapping(target = "id", expression = "java(RestaurantId.of(jpa.getId()))")
    @Mapping(target = "ownerId", expression = "java(OwnerId.of(jpa.getOwner().getId()))")
    @Mapping(target = "address", expression = "java(toDomainAddress(jpa.getAddress()))")
    @Mapping(target = "email", expression = "java(jpa.getEmail())")
    @Mapping(target = "pictureURL", expression = "java(jpa.getPictureUrl())")
    @Mapping(target = "defaultPrepTime", expression = "java(jpa.getDefaultPrepMinutes())")
    @Mapping(target = "cuisineType", expression = "java(jpa.getCuisineType())")
    @Mapping(target = "openingHours", expression = "java(toDomainOpeningHours(jpa.getOpeningHours()))")
    Restaurant toDomain(RestaurantJpaEntity jpa);

    // ---- Address VO mappings
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

    // ---- OpeningHours VO mappings
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
