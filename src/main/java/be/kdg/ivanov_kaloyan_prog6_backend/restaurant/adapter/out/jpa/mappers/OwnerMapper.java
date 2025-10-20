package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.OwnerJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OwnerId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.RestaurantId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = { RestaurantId.class, OwnerId.class })
public interface OwnerMapper {

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    OwnerJpaEntity toJpa(Owner domain);

    @Mapping(target = "id", expression = "java(OwnerId.of(jpa.getId()))")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    Owner toDomain(OwnerJpaEntity jpa);
}
