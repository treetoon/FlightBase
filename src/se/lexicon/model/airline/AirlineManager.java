package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.FoodManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AirlineManager {
    private List<Airplane> airplaneList;
    private List<Reservation> reservationsList;

    private FoodManager foodManager;

    public AirlineManager() {
        airplaneList = new ArrayList<>();
        foodManager = new FoodManager();
        reservationsList = new ArrayList<>();

        //default plane
        addPlane(new Airplane(10, 20000, 5000, "London"));
    }

    public FoodManager getFoodManager() {
        return foodManager;
    }

    public Airplane getPlane(int index) {
        if (index < airplaneList.size())
            return airplaneList.get(index);

        return null;
    }

    public int getTicketPrice(int flightNr, SectionType sectionType) {
        for (Airplane airplane : airplaneList) {
            if (airplane.getFlightNr()==flightNr) {
                if (sectionType==SectionType.BUSINESS) {
                    return airplane.getBusinessSectionPrice();
                } else if (sectionType==SectionType.ECONOMY) {
                    return airplane.getEconomySectionPrice();
                }
            }
        }
        return 0;
    }

    public List<Airplane> getPlanesList() {
        return airplaneList;
    }

    public List<Reservation> getReservationsList() {
        return reservationsList;
    }

    public void addPlane(Airplane plane) {
        airplaneList.add(plane);
    }

    public void list() {

    }

    public void addSeat() {

    }

    public void removeSeat() {

    }

    public int createReservation(String firstName, String lastName, String address, String phoneNr, String seatNr, int flightNr, SectionType sectionType) {
        Customer customer=new Customer(firstName, lastName, address, phoneNr);
        Ticket ticket = new Ticket(seatNr, flightNr, getTicketPrice(flightNr, sectionType), sectionType);

        System.out.println(ticket.toString());



        Reservation reservation = new Reservation(customer, ticket);
        reservationsList.add(reservation);

        return reservationsList.size();
    }

    public void editReservation() {

    }

    public int profitCalc() {
        Integer total = reservationsList.stream()
                .reduce(0, (sum, reservation) -> sum += reservation.calculateTotalPrice(),
                        (sum1, sum2) -> sum1+sum2);

        return total;
    }

    private void reserveSeat() {

    }

    public int numberOfAvailableBusinessSeats() {
        return 0;
    }
}
