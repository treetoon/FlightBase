package se.lexicon.model.airline;

import se.lexicon.model.food.FoodManager;

import java.util.ArrayList;
import java.util.List;

public class AirlineManager {
    private List<Airplane> airplaneList;
    private List<Reservation> reservationsList;

    private FoodManager foodManager;
   // private Iterator<Airplane> it;

    public AirlineManager() {
        airplaneList = new ArrayList<>();
        //it = airplaneList.iterator();

        foodManager=new FoodManager();

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
    public void list() {}

    public void addSeat() {}
    public void removeSeat() {}

    public void createReservation() {
        //create reservationNumber
    }
    public void editReservation() {}

    public void profitCalc() {}

    private void reserveSeat() {}

    public int numberOfAvailableBusinessSeats() {

        return 0;
    }
//
//    public Airplane next() {
//        return it.next();
//    }
//
//    public boolean hasNext() {
//        return it.hasNext();
//    }
}
