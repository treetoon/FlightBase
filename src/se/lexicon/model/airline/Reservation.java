package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.types.BusinessFoodType;
import se.lexicon.model.food.Food;

import java.util.List;

public class Reservation {
    private String reservationNumber;
    private SectionType sectionType;
    private List<Food> foodList;
    private Customer customer;

    public Reservation() {
    }

    public Reservation(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public void createFoodReservation() {


    }
}
