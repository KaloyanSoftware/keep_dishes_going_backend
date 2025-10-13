package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
@Qualifier("jpa")
public class ItemAvailabilityProjectionAdapter {
}
