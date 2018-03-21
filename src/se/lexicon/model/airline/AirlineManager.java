package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.FoodManager;

import java.util.ArrayList;
import java.util.List;

public class AirlineManager {
    private List<Airplane> airplaneList;
    private List<Reservation> reservationsList;

    private FoodManager foodManager;

    public FoodManager getFoodManager() {
        return foodManager;
    }
    // private Iterator<Airplane> it;

    public AirlineManager() {
        airplaneList = new ArrayList<>();
        foodManager = new FoodManager();
        reservationsList = new ArrayList<>();

        //default plane
        addPlane(new Airplane(21));
    }

    public void addPlane(Airplane plane) {
        airplaneList.add(plane);
    }

    public Airplane getPlane(int index) {
        if (index < airplaneList.size())
            return airplaneList.get(index);

        return null;
    }

    public List<Airplane> getPlanesList() {
        return airplaneList;
    }

    public List<Reservation> getReservationsList() {
        return reservationsList;
    }

    public void list() {

    }

    public void addSeat() {

    }

    public void removeSeat() {

    }

    public int createReservation(String firstName, String lastName, String address, String phoneNr, String seatNr, int flightNr, int price, SectionType sectionType) {
        Customer customer=new Customer(firstName, lastName, address, phoneNr);
        Ticket ticket = new Ticket(seatNr, flightNr, price, sectionType);

        Reservation reservation = new Reservation(customer, ticket);
        reservationsList.add(reservation);

        return reservationsList.size();
    }

    public void editReservation() {

    }

    public void profitCalc() {

    }

    private void reserveSeat() {

    }

    public int numberOfAvailableBusinessSeats() {
        return 0;
    }
}
