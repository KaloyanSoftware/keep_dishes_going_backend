package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.embeddables;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class DishSnapshotEmbeddable {
    private String name;

    @Enumerated(EnumType.STRING)
    private DishType type;

    private String description;

    private Integer price;

    private String pictureUrl;

}
