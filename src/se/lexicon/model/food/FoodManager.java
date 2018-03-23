package se.lexicon.model.food;

import java.util.ArrayList;
import java.util.List;

public class FoodManager {

    private List<Food> economyFoodList;
    private List<Food> businessFoodList;

    public FoodManager(){
        economyFoodList=new ArrayList<>();
        businessFoodList=new ArrayList<>();

        economyFoodList.add(new Food(60, "Meatballs"));
        economyFoodList.add(new Food(70, "Spaghetti"));
        economyFoodList.add(new Food(80, "Pizza"));
        economyFoodList.add(new Food(20, "Coca-Cola"));
        economyFoodList.add(new Food(30, "Mineral water"));

        businessFoodList.add(new Food(150, "Entrecote"));
        businessFoodList.add(new Food(200, "Froglegs"));
        businessFoodList.add(new Food(200, "Caviar"));
        businessFoodList.add(new Food(100, "Wine"));
        businessFoodList.add(new Food(150, "Champagne"));
    }

    public List<Food> getEconomyFoodList() {
        return economyFoodList;
    }

    public List<Food> getBusinessFoodList() {
        return businessFoodList;
    }

}
