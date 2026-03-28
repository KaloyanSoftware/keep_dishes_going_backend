package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "dish", schema = "restaurant")
public class DishJpaEntity {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuJpaEntity menu;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private Dish.State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status", nullable = false)
    private Dish.StockStatus stockStatus;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "dish_tag",
            schema = "restaurant",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "uuid")
    )

    @Enumerated(EnumType.STRING)
    @Column(name = "tag", nullable = false)
    private List<FoodTag> tags;

    public DishJpaEntity(UUID id, Dish.State state,
                         Dish.StockStatus stockStatus, String name,
                         DishType type, String description, Double price,
                         String pictureUrl, List<FoodTag> tags) {
        setId(id);
        setState(state);
        setStockStatus(stockStatus);
        setName(name);
        setType(type);
        setDescription(description);
        setPrice(price);
        setPictureUrl(pictureUrl);
        setTags(tags);
    }

    public DishJpaEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMenuId() {
        return menu != null ? menu.getId() : null;
    }

    public MenuJpaEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuJpaEntity menu) {
        this.menu = menu;
    }

    public Dish.State getState() {
        return state;
    }

    public void setState(Dish.State state) {
        this.state = state;
    }

    public Dish.StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(Dish.StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<FoodTag> getTags() {
        return tags;
    }

    public void setTags(List<FoodTag> tags) {
        this.tags = tags;
    }
}
