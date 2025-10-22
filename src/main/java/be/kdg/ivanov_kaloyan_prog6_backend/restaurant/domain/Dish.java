package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Dish {

    public enum State {
        UNPUBLISHED,
        PUBLISHED
    }

    public enum StockStatus {
        IN_STOCK,
        OUT_OF_STOCK

    }

    private DishId id;

    private MenuId menuId;

    private State state;

    private StockStatus stockStatus;

    private String name;

    private DishType type;

    private List<FoodTag> tags = new ArrayList<>();

    private String description;

    private BigDecimal price;

    private String pictureURL;

    public Dish() {}

    public Dish(DishDraft draft, MenuId menuId) {
        this.id = Objects.requireNonNullElseGet(draft.getDishId(), DishId::create);
        this.menuId = menuId;
        this.name = draft.getName();
        this.type = draft.getType();
        this.tags = draft.getTags();
        this.description = draft.getDescription();
        this.price = draft.getPrice();
        this.pictureURL = draft.getPictureURL();
    }

    public Dish(DishId id, MenuId menuId, State state, StockStatus stockStatus, String name, DishType type,
                List<FoodTag> tags, String description, BigDecimal price, String pictureURL) {
        this.id = id;
        this.menuId = menuId;
        this.state = state;
        this.stockStatus = stockStatus;
        this.name = name;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public void publish(){
        changeState(State.PUBLISHED);
    }

    public void unpublish(){
        changeState(State.UNPUBLISHED);
    }

    public void markOutOfStock(){
        changeStockStatus(StockStatus.OUT_OF_STOCK);
    }

    public void markInStock(){
        changeStockStatus(StockStatus.IN_STOCK);
    }

    public boolean outOfStock(){
        return this.stockStatus.equals(StockStatus.OUT_OF_STOCK);
    }

    public boolean published(){
        return this.state.equals(State.PUBLISHED);
    }

    public boolean orderable(){
        return state.equals(State.PUBLISHED) && stockStatus.equals(StockStatus.IN_STOCK);
    }

    public State getState() {
        return state;
    }

    public void changeState(State state) {
        this.state = state;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void changeStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
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

    public MenuId getMenuId() {
        return menuId;
    }
}
