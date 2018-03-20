package se.lexicon.model.food;

public abstract class Food {
    private int price;

    public Food(int price) {
        this.price=price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
