package se.lexicon.model.airline;

import se.lexicon.model.food.Food;
import se.lexicon.utilities.SectionType;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private String reservationNumber;           //autogenerera denna?
    private String seatNumber;
    private String flightNumber;
    private SectionType sectionType;
    private List<Food> foodList;
    private Customer customer;

    public Reservation(String seatNumber, String flightNumber, SectionType sectionType,
                       List<Food> foodList, Customer customer) {
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.sectionType = sectionType;
        this.customer = customer;
        foodList = new ArrayList<>();
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
