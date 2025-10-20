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
        this.id = setDishId(draft.getDishId());
        this.menuId = menuId;
        this.name = draft.getName();
        this.type = draft.getType();
        this.tags = draft.getTags();
        this.description = draft.getDescription();
        this.price = draft.getPrice();
        this.pictureURL = draft.getPictureURL();
    }

    public void publish(){
        setState(State.PUBLISHED);
    }

    public void unpublish(){
        setState(State.UNPUBLISHED);
    }

    public void markOutOfStock(){
        setStockStatus(StockStatus.OUT_OF_STOCK);
    }

    public void markInStock(){
        setStockStatus(StockStatus.IN_STOCK);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
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

    public void setMenuId(MenuId menuId) {
        this.menuId = menuId;
    }

    private DishId setDishId(DishId id){
        return Objects.requireNonNullElseGet(id, DishId::create);
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

    public void setId(DishId id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }


}
