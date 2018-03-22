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
    private int totalFoodPrice;
    private Ticket ticket;

    public List<Food> getFoodList() {
        return foodList;
    }

    public Reservation(Customer customer, Ticket ticket) {
        numberOfReservations++;
        this.customer = customer;
        foodList = new ArrayList<>();
        this.ticket=ticket;
        reservationNumber=numberOfReservations;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    //calculates&sets total price (food+ticket)
    public int calculateTotalPrice() {
        totalPrice = 0; //reset
        return totalPrice += ticket.getPrice() + calculateTotalFoodPrice();
    }

    public int calculateTotalFoodPrice() {
        totalFoodPrice = 0; //reset

        for (Food food : foodList) {
            totalFoodPrice += food.getPrice();
        }
        return totalFoodPrice;
    }

    public void addFoodItem(Food food) {
        foodList.add(food);
    }

    public void removeFoodItem(int index) {
        foodList.remove(index);

    }

    public Customer getCustomer() {
        return customer;
    }

    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNumber=" + reservationNumber +
                ", foodList=" + foodList +
                ", customer=" + customer +
                ", totalPrice=" + totalPrice +
                ", ticket=" + ticket +
                '}';
    }
}
