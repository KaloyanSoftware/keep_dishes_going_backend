package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "dish", schema = "restaurant")
public class DishJpaEntity {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", referencedColumnName = "uuid", nullable = false)
    private MenuJpaEntity menu;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility", nullable = false)
    private Dish.Visibility visibility;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DishType type;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "picture_url")
    private String pictureUrl;

    @ElementCollection
    @CollectionTable(
            name = "dish_tag",
            schema = "restaurant",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "uuid")
    )

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", nullable = false)
    private Set<FoodTag> tags = new HashSet<>();

}

