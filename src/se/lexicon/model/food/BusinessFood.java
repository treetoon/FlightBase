package se.lexicon.model.food;

public class BusinessFood extends Food {
    private String name;

    public BusinessFood(String name, int price) {
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
