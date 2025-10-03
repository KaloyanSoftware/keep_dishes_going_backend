package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

public final class Dish {

    public enum Visibility { UNPUBLISHED, PUBLISHED, OUT_OF_STOCK }

    private final DishId id;

    private Visibility visibility;

    /** What customers see right now; null until first publish */
    private DishSnapshot live;

    /** What the owner is editing right now; never null after creation */
    private DishSnapshot draft;

    public Dish(DishSnapshot draft, DishSnapshot live) {
        this(DishId.create(), draft, live, Visibility.UNPUBLISHED);
    }

    public Dish(DishId id, DishSnapshot draft, DishSnapshot live, Visibility visibility) {
        this.id = id;
        this.draft = draft;
        this.live = live;
        this.visibility = visibility;
    }


    /** Edit the draft (does not touch LIVE). */
    /*public void editDraft(DishSnapshot newDraft) {
        this.draft = Objects.requireNonNull(newDraft, "newDraft");
    }*/

    /** Delete/Discard draft changes (revert draft to live). */
    /*public void discardDraftChanges() {
        this.draft = this.live; // may set draft to null if never published
    }*/

    /** Publish: copy DRAFT → LIVE, flip visibility if needed. */
    /*public void publishFromDraft() {
        if (draft == null) throw new IllegalStateException("No draft to publish");
        this.live = this.draft;
        if (visibility == Visibility.UNPUBLISHED) {
            visibility = Visibility.PUBLISHED;
        }
    }*/

    /** Unpublish: dish not visible/orderable to customers (LIVE snapshot kept for history if desired). */
    /*public void unpublish() {
        visibility = Visibility.UNPUBLISHED;
    }*/

    /** Out of stock is allowed only from PUBLISHED (still visible but not orderable). */
    /*public void markOutOfStock() {
        if (visibility != Visibility.PUBLISHED) {
            throw new IllegalStateException("Can only mark OUT_OF_STOCK from PUBLISHED");
        }
        visibility = Visibility.OUT_OF_STOCK;
    }*/

    /** Back in stock is allowed only from OUT_OF_STOCK. */
    /*public void backInStock() {
        if (visibility != Visibility.OUT_OF_STOCK) {
            throw new IllegalStateException("Can only mark back in stock from OUT_OF_STOCK");
        }
        visibility = Visibility.PUBLISHED;
    }*/

    /** Whether there are un-published edits. */
    /*public boolean hasPendingChanges() {
        return !Objects.equals(live, draft);
    }*/

    public DishId dishId() { return id; }
    public Visibility visibility() { return visibility; }
    public DishSnapshot live() { return live; }
    public DishSnapshot draft() { return draft; }
}
