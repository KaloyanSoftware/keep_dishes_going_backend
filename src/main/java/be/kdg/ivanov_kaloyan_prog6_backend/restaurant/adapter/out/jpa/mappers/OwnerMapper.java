package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.OwnerJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OwnerId;
import org.mapstruct.*;

@Mapper(componentModel = "spring", imports = {OwnerId.class})
public interface OwnerMapper {

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    OwnerJpaEntity toJpa(Owner domain);

    @ObjectFactory
    default Owner createOwner(OwnerJpaEntity jpa) {
        return new Owner(
                OwnerId.of(jpa.getId()),
                jpa.getFirstName(),
                jpa.getLastName(),
                jpa.getEmail()
        );
    }

    default Owner toDomain(OwnerJpaEntity jpa) {
        return createOwner(jpa);
    }
}
