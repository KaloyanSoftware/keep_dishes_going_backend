package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.LocationEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.RestaurantProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Location;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RestaurantProjectionMapper {

    @Mapping(target = "id", source = "restaurantId")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    @Mapping(target = "defaultPrepMinutes", source = "defaultPrepTime")
    @Mapping(target = "cuisineType", source = "cuisineType")
    @Mapping(target = "location", expression = "java(toJpaLocation(domain.getLocation()))")
    RestaurantProjectionJpaEntity toJpa(RestaurantProjection domain);

    @Mapping(target = "restaurantId", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "pictureURL", source = "pictureUrl")
    @Mapping(target = "defaultPrepTime", source = "defaultPrepMinutes")
    @Mapping(target = "cuisineType", source = "cuisineType")
    @Mapping(target = "location", expression = "java(toDomainLocation(jpa.getLocation()))")
    RestaurantProjection toDomain(RestaurantProjectionJpaEntity jpa);

    default LocationEmbeddable toJpaLocation(Location location) {
        if (location == null) return null;
        return new LocationEmbeddable(
                location.street(),
                location.number(),
                location.postalCode(),
                location.city(),
                location.country()
        );
    }

    default Location toDomainLocation(LocationEmbeddable emb) {
        if (emb == null) return null;
        return new Location(
                emb.getStreet(),
                emb.getNumber(),
                emb.getPostalCode(),
                emb.getCity(),
                emb.getCountry()
        );
    }
}
