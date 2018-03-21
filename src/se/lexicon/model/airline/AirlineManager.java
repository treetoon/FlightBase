package se.lexicon.model.airline;

import se.lexicon.model.food.BusinessFood;
import se.lexicon.model.food.EconomyFood;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AirlineManager {
    private List<Airplane> airplaneList;
    private List<Reservation> reservationsList;

    private List<BusinessFood> businessFoodList;
    private List<EconomyFood> economyFoodList;
   // private Iterator<Airplane> it;

    public AirlineManager() {
        airplaneList = new ArrayList<>();
        //it = airplaneList.iterator();
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
