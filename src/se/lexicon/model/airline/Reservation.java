package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private static int numberOfReservations = 0;
    private int reservationNumber;
    private List<Food> foodList;
    private Customer customer;
    private int totalPrice;
    private Ticket ticket;

    public List<Food> getFoodList() {
        return foodList;
    }

    public Reservation(Customer customer, Ticket ticket) {
        numberOfReservations++;
        this.customer = customer;
        foodList = new ArrayList<>();
        this.ticket=ticket;
    }

    public int calculateTotalPrice() {
        //set totalPriceof listfood & ticket
        return 0;
    }

    public void addFoodItem(Food food) {
        foodList.add(food);
    }




}
