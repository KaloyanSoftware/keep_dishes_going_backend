package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;

public interface SaveOwnerPort {

    void save(Owner owner);
}
