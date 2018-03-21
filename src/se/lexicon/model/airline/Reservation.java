package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String reservationNumber;           //autogenerera denna?
    private List<Food> foodList;
    private Customer customer;
    private int totalPrice;

    public Reservation(List<Food> foodList, Customer customer) {


        this.customer = customer;
        foodList = new ArrayList<>();
    }



}
