package se.lexicon.model.food;

public class EconomyFood extends Food {
    private String name;

    @Override
    public String toString() {
        return "EconomyFood{" +
                "name=" + name + " price=" + super.getPrice() +
                '}';
    }

    public EconomyFood(String name, int price) {
        super(price);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
