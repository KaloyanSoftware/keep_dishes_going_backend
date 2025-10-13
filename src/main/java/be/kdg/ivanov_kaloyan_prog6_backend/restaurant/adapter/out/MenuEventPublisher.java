package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MenuEventPublisher implements UpdateMenuPort {

    private final ApplicationEventPublisher applicationEventPublisher;

    public MenuEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public Menu update(Menu menu) {
        menu.getEvents().forEach(applicationEventPublisher::publishEvent);
        return menu;
    }
}
