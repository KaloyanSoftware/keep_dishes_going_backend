package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.mappers.OwnerMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.repositories.OwnerJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOwnerPort;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OwnerJpaAdapter implements LoadOwnerPort {
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
}
