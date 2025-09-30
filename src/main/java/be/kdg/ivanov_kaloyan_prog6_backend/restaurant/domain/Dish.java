package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.math.BigDecimal;
import java.util.*;

public class Dish {
   private final DishId dishId;

   private String name;

   private DishType type;

   private Set<FoodTag> tags =  new HashSet<>();

   private Visibility visibility = Visibility.UNPUBLISHED;

   private String description;

   private BigDecimal price;

   private String pictureURL;

   public enum Visibility {
       UNPUBLISHED, PUBLISHED, OUT_OF_STOCK
   }

    public Dish(String name, DishType type, String description,
                BigDecimal price, String pictureURL) {
        this.dishId = DishId.create();
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public void outOfStock(){
       this.visibility = Visibility.OUT_OF_STOCK;
    }
}
