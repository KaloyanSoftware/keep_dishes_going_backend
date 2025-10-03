package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;

public interface SaveMenuPort {

    Menu save(Menu menu);
}
