package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String reservationNumber;
    private SectionType sectionType;
    private List<Food> foodList;
    private Customer customer;

    //Måste ha en String för platsnret också?

    public Reservation(SectionType sectionType, List<Food> foodList, Customer customer) {
        this.sectionType=sectionType;
        this.customer=customer;
        foodList=new ArrayList<>();
    }

    public Reservation(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public void createFoodReservation() {


    }
}
