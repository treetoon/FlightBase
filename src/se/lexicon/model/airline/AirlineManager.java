package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.FoodManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AirlineManager {
    private List<Airplane> airplaneList;
    private List<Reservation> reservationsList;

    private FoodManager foodManager;
    private final double companyProfitPercentage = .3; //implement big decimal later

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

    public int getIncome() {
        return calculateIncome();
    }

    public int getProfit() {
        return (int) ((double) calculateIncome() * companyProfitPercentage);
    }

    public Airplane getPlaneByFlightNr(int flightNr) {
        for (Airplane plane : airplaneList) {
            if (plane.getFlightNr() == flightNr) {
                return plane;
            }
        }
        return null;
    }

    public int getTicketPrice(int flightNr, SectionType sectionType) {
        for (Airplane airplane : airplaneList) {
            if (airplane.getFlightNr() == flightNr) {
                if (sectionType == SectionType.BUSINESS) {
                    return airplane.getBusinessSectionPrice();
                } else if (sectionType == SectionType.ECONOMY) {
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

    public boolean addPlane(Airplane plane) {
        if (plane != null) {
            airplaneList.add(plane);
            return true;
        } else {
            return false;
        }
    }

    public boolean removePlane(int flightNr) {
        Iterator<Airplane> it = airplaneList.iterator();

        while (it.hasNext()) {
            if (it.next().getFlightNr() == flightNr) {
                it.remove(); //remove current element
                return true;
            }
        }
        return false;
    }

    public boolean addSeat(int flightNr, int numOfSeats) {
        for (Airplane plane : airplaneList) {
            if (plane.getFlightNr() == flightNr) {
                plane.addSeat(numOfSeats);
                return true;
            }
        }
        return false;
    }

    public boolean removeSeat(int flightNr, int numOfSeats) {
        for (Airplane plane : airplaneList) {
            if (plane.getFlightNr() == flightNr) {
                plane.removeSeat(numOfSeats);
                return true;
            }
        }
        return false;
    }

    public int createReservation(String firstName, String lastName, String address, String phoneNr,
                                 String seatNr, int flightNr, SectionType sectionType) {
        Customer customer = new Customer(firstName, lastName, address, phoneNr);
        Ticket ticket = new Ticket(seatNr, flightNr, getTicketPrice(flightNr, sectionType), sectionType);

        Reservation reservation = new Reservation(customer, ticket);
        reservationsList.add(reservation);

        return reservation.getReservationNumber();
    }

    /**
     * Removes reservation and unreserves seat on airplane
     *
     * @param reservationNr
     * @return true if reservation is deleted
     */
    public boolean deleteReservation(int reservationNr) {
        int flightNr = 0;
        String seatNr = null;

        if (reservationNr > 0) {
            int index = 0;

            for (Reservation reservation : reservationsList) {
                if (reservation.getReservationNumber() == reservationNr) {
                    flightNr = reservationsList.get(index).getTicket().getFlightNumber();
                    seatNr = reservationsList.get(index).getTicket().getSeatNumber();

                    reservationsList.remove(index);

                    if (airplaneList.get(flightNr - 1).unreserveSeat(seatNr)) {
                        return true;
                    }
                }
                index++;
            }
        }
        return false;
    }

    /**
     * Calculates all food items and tickets for all customers
     * @return int as currency
     */
    private int calculateIncome() {
        return reservationsList.stream().reduce(0,
                (sum, reservation) -> sum += reservation.calculateTotalPrice(),
                (sum1, sum2) -> sum1 + sum2);
    }
}
