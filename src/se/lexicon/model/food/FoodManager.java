package se.lexicon.model.food;

import java.util.ArrayList;
import java.util.List;

public class FoodManager {

    private List<Food> economyFoodList;
    private List<Food> businessFoodList;

    public FoodManager(){
        economyFoodList=new ArrayList<>();
        businessFoodList=new ArrayList<>();

        economyFoodList.add(new Food(100, "Meatballs"));
        economyFoodList.add(new Food(200, "Spaghetti"));
        economyFoodList.add(new Food(200, "Pizza"));

        businessFoodList.add(new Food(100, "Entrecote"));
        businessFoodList.add(new Food(200, "Froglegs"));
        businessFoodList.add(new Food(200, "Caviar"));
    }

    public List<Food> getEconomyFoodList() {
        return economyFoodList;
    }

    public List<Food> getBusinessFoodList() {
        return businessFoodList;
    }

}
