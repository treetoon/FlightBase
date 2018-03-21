package se.lexicon.model.food;

import java.util.ArrayList;
import java.util.List;

public class FoodManager {

    private List<Food> economyFoodList;
    private List<Food> businessFoodList;

    public FoodManager(){
        economyFoodList=new ArrayList<>();
        businessFoodList=new ArrayList<>();

        economyFoodList.add(new Food(100, "köttbullar"));
        economyFoodList.add(new Food(200, "spaghetti"));

        businessFoodList.add(new Food(100, "entrecote"));
        businessFoodList.add(new Food(200, "grodlår"));
    }
}
