package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOwnerPort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class OwnerInMemoryAdapter implements LoadOwnerPort {

    private final Map<UUID, Owner> owners = new HashMap<>();

    @Override
    public Optional<Owner> loadBy(UUID uuid) {
        return Optional.ofNullable(owners.get(uuid));
    }
}
