package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.embeddables.DishSnapshotEmbeddable;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
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
    @JoinColumn(name = "restaurant_id", referencedColumnName = "uuid", nullable = false)
    private RestaurantJpaEntity restaurant;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility", nullable = false)
    private Dish.Visibility visibility;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name",         column = @Column(name = "live_name")),
            @AttributeOverride(name = "type",         column = @Column(name = "live_type")),
            @AttributeOverride(name = "description",  column = @Column(name = "live_description")),
            @AttributeOverride(name = "price",        column = @Column(name = "live_price")),
            @AttributeOverride(name = "pictureUrl",   column = @Column(name = "live_picture_url"))
    })
    private DishSnapshotEmbeddable live;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name",         column = @Column(name = "draft_name")),
            @AttributeOverride(name = "type",         column = @Column(name = "draft_type")),
            @AttributeOverride(name = "description",  column = @Column(name = "draft_description")),
            @AttributeOverride(name = "price",        column = @Column(name = "draft_price")),
            @AttributeOverride(name = "pictureUrl",   column = @Column(name = "draft_picture_url"))
    })
    private DishSnapshotEmbeddable draft;

    @ElementCollection
    @CollectionTable(
            name = "dish_draft_tag",
            schema = "restaurant",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "uuid")
    )

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", nullable = false)
    private Set<FoodTag> draftTags = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "dish_live_tag",
            schema = "restaurant",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "uuid")
    )

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", nullable = false)
    private Set<FoodTag> liveTags = new HashSet<>();

}

