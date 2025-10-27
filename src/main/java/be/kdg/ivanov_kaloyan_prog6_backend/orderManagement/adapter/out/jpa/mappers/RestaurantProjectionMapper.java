package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.LocationEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities.RestaurantProjectionJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.Location;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RestaurantProjectionMapper {

    @Mappings({
            @Mapping(target = "id", source = "restaurantId"),
            @Mapping(target = "location", expression = "java(toEmbeddable(domain.getLocation()))"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "pictureURL", source = "pictureURL"),
            @Mapping(target = "defaultPrepTime", source = "defaultPrepTime"),
            @Mapping(target = "cuisineType", source = "cuisineType"),
            @Mapping(target = "open", source = "open")
    })
    RestaurantProjectionJpaEntity toJpa(RestaurantProjection domain);

    @ObjectFactory
    default RestaurantProjection rehydrate(RestaurantProjectionJpaEntity jpa) {
        return RestaurantProjection.rehydrate(
                jpa.getId(),
                toDomain(jpa.getLocation()),
                jpa.getEmail(),
                jpa.getPictureURL(),
                jpa.getDefaultPrepTime(),
                jpa.getCuisineType(),
                jpa.isOpen()
        );
    }

    default RestaurantProjection toDomain(RestaurantProjectionJpaEntity jpa) {
        return rehydrate(jpa);
    }

    default LocationEmbeddable toEmbeddable(Location location) {
        return new LocationEmbeddable(
                location.street(),
                location.number(),
                location.postalCode(),
                location.city(),
                location.country()
        );
    }

    default Location toDomain(LocationEmbeddable emb) {
        return new Location(
                emb.getStreet(),
                emb.getNumber(),
                emb.getPostalCode(),
                emb.getCity(),
                emb.getCountry()
        );
    }
}
