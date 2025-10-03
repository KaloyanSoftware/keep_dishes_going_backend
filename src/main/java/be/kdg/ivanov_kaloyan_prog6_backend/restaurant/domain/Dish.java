package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.math.BigDecimal;
import java.util.EnumSet;

public final class Dish {

    public enum Visibility { UNPUBLISHED, PUBLISHED, OUT_OF_STOCK }

    private DishId id;

    private Visibility visibility;

    private String name;

    private DishType type;

    EnumSet<FoodTag> tags;

    private String description;

    private BigDecimal price;

    private String pictureURL;

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
}
