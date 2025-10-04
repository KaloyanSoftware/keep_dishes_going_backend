package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class Dish {

    public enum Visibility { UNPUBLISHED, PUBLISHED, OUT_OF_STOCK }

    private DishId id;

    private Visibility visibility;

    private String name;

    private DishType type;

    private List<FoodTag> tags = new ArrayList<>();

    private String description;

    private BigDecimal price;

    private String pictureURL;

    public static Dish rehydrate(
            DishId id,
            Visibility visibility,
            String name,
            DishType type,
            List<FoodTag> tags,
            String description,
            BigDecimal price,
            String pictureURL
    ) {
        Dish d = new Dish();
        d.id = id;
        d.visibility = visibility;
        d.name = name;
        d.type = type;
        if (tags != null) d.tags = new ArrayList<>(tags);
        d.description = description;
        d.price = price;
        d.pictureURL = pictureURL;
        return d;
    }

    public void publish(){
        setVisibility(Visibility.PUBLISHED);
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public DishId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DishType getType() {
        return type;
    }

    public List<FoodTag> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPictureURL() {
        return pictureURL;
    }
}
