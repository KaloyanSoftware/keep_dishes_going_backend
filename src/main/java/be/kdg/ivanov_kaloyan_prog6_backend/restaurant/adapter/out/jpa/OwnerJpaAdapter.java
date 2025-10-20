package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.OwnerJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.OwnerMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.OwnerJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOwnerPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveOwnerPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("jpa")
@Profile("jpa")
public class OwnerJpaAdapter implements LoadOwnerPort, SaveOwnerPort {
    private final OwnerJpaRepository owners;

    private final OwnerMapper mapper;

    public OwnerJpaAdapter(OwnerMapper mapper, OwnerJpaRepository owners) {
        this.mapper = mapper;
        this.owners = owners;
    }

    @Override
    public Optional<Owner> loadBy(UUID id) {
        return this.owners.findById(id).map(mapper::toDomain);
    }

    @Override
    public void save(Owner owner) {
        OwnerJpaEntity jpa = mapper.toJpa(owner);

        mapper.toDomain(owners.save(jpa));
    }
}
