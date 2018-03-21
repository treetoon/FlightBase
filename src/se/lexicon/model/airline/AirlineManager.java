package se.lexicon.model.airline;

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
        addPlane(new Airplane("1", 10));
    }

    public void addPlane(Airplane plane) {
        airplaneList.add(plane);
    }

    public Airplane getPlane(int index) {
        return airplaneList.get(index);
    }

    public List<Airplane> getPlanesList() {
        return airplaneList;
    }

    public void list() {

    }

    public void addSeat() {

    }

    public void removeSeat() {

    }

    public void createReservation() {
        //create reservationNumber
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
