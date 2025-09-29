package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOwnerPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveOwnerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerInMemoryAdapter implements LoadOwnerPort, SaveOwnerPort {


    private static final Logger log = LoggerFactory.getLogger(OwnerInMemoryAdapter.class);

    private final Map<UUID, Owner> owners = new HashMap<>();

    public OwnerInMemoryAdapter() {
        //seeded owner for testing
        Owner owner = new Owner();
        owners.put(owner.getOwnerId().uuid(), owner);

        log.error("Seeded owner id: {}",owner.getOwnerId().uuid().toString());
    }

    @Override
    public Optional<Owner> loadBy(UUID uuid) {
        return Optional.ofNullable(owners.get(uuid));
    }

    @Override
    public void save(Owner owner) {
        this.owners.put(id, new Owner());
    }
}
