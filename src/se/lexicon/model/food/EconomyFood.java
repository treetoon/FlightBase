package se.lexicon.model.food;

import se.lexicon.model.food.types.EconomyFoodType;

public class EconomyFood extends Food {
    private EconomyFoodType name;

    @Override
    public String toString() {
        return "EconomyFood{" +
                "name=" + name + " price=" + super.getPrice() +
                '}';
    }

    public EconomyFood(EconomyFoodType name, int price) {
        super(price);
        this.name = name;
    }

    public EconomyFoodType getName() {
        return name;
    }

    public void setName(EconomyFoodType name) {
        this.name = name;
    }
}
