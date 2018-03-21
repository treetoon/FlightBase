package se.lexicon.model.food;

public class Food {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public Food(int price, String name) {
        this.name=name;
        this.price=price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
