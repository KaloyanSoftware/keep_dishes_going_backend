package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.OwnerJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OwnerId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.RestaurantId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = { RestaurantId.class, OwnerId.class })
public interface OwnerMapper {

    // Domain -> JPA
    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "firstName", expression = "java(domain.getFirstName())")
    @Mapping(target = "lastName", expression = "java(domain.getLastName())")
    OwnerJpaEntity toJpa(Owner domain);

    // JPA -> Domain
    @Mapping(target = "id", expression = "java(OwnerId.of(jpa.getId()))")
    @Mapping(target = "firstName", expression = "java(jpa.getFirstName())")
    @Mapping(target = "lastName", expression = "java(jpa.getLastName())")
    Owner toDomain(OwnerJpaEntity jpa);
}
