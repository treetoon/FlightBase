package se.lexicon.model.food;

import se.lexicon.model.food.types.BusinessFoodType;

public class BusinessFood extends Food {
    private BusinessFoodType name;

    public BusinessFood(BusinessFoodType name, int price) {
        super(price);
        this.name = name;
    }

    public BusinessFoodType getName() {
        return name;
    }

    public void setName(BusinessFoodType name) {
        this.name = name;
    }
}
