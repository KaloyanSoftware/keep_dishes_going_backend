package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;

public interface SaveMenuPort {

    void save(Menu menu);
}
