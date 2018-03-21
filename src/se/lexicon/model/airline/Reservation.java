package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private static int numberOfReservations=0;
    private int reservationNumber;
    private List<Food> foodList;
    private Customer customer;
    private int totalPrice;

    public List<Food> getFoodList() {
        return foodList;
    }

    public Reservation(Customer customer) {
        numberOfReservations++;
        this.customer = customer;
        foodList = new ArrayList<>();
    }



}
