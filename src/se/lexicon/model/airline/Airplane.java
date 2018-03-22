package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;

import java.util.*;

public class Airplane {
    private int flightNr;
    private static int flightCounter = 0;

    private Map<String, Boolean> businessSeatList;
    private Map<String, Boolean> economySeatList;

    private int businessSectionPrice;
    private int economySectionPrice;
    private String destination;

    public Airplane(int numOfSeats, int businessSectionPrice, int economySectionPrice, String destination) {
        flightCounter++;
        flightNr = flightCounter;

        businessSeatList = new LinkedHashMap<>();
        economySeatList = new LinkedHashMap<>();

        this.businessSectionPrice = businessSectionPrice;
        this.economySectionPrice = economySectionPrice;
        this.destination = destination;

        assignSeats(numOfSeats);
    }

    public void assignSeats(int numOfSeats) {
        businessSeatList.clear();
        economySeatList.clear();

        int numberOfRows = numOfSeats / 6;
        int remainingSeats = numOfSeats % 6;

        for (int i = 1; i <= numberOfRows; i++) {
            businessSeatList.put(i + "A", false);
            businessSeatList.put(i + "B", false);
            businessSeatList.put(i + "C", false);

            economySeatList.put(i + "D", false);
            economySeatList.put(i + "E", false);
            economySeatList.put(i + "F", false);
        }

        //If there are remaining seats to be assigned (a last row)
        if (remainingSeats > 0) {
            char seatLetter = 'A';
            int lastRowNumber = numberOfRows + 1;

            for (int i = 1; i <= remainingSeats; i++) {
                if (seatLetter == 'A' || seatLetter == 'B' || seatLetter == 'C') {
                    String s = "" + lastRowNumber + seatLetter;
                    businessSeatList.put(s, false);
                } else {
                    String s = "" + lastRowNumber + seatLetter;
                    economySeatList.put(s, false);
                }
                seatLetter++;
            }
        }
    }

    //Förutsätter att planen är i renoveringsläge och det ej finns bokningar..
    public void addSeat(int numOfSeats) {
        int currentTotalSeats = businessSeatList.size()+ economySeatList.size();
        int newTotalSeats = currentTotalSeats + numOfSeats;

        assignSeats(newTotalSeats);
    }

    public boolean removeSeat(int numOfSeats) {
        int currentTotalSeats = businessSeatList.size()+ economySeatList.size();
        int newTotalSeats = currentTotalSeats - numOfSeats;

        if (newTotalSeats>0) {
            assignSeats(newTotalSeats);
            return true;
        }

        return false;
    }

    public int getFlightNr() {
        return flightNr;
    }

    public int getBusinessSectionPrice() {
        return businessSectionPrice;
    }

    public int getEconomySectionPrice() {
        return economySectionPrice;
    }

    public int totalSeatsOfAirplane() {
        return (this.businessSeatList.size() + this.economySeatList.size());
    }

    public int numberOfAvailableBusinessSeats() {
        return numberOfAvailableSeats(SectionType.BUSINESS);
    }

    public int numberOfAvailableEconomySeats() {
        return numberOfAvailableSeats(SectionType.ECONOMY);
    }

    private int numberOfAvailableSeats(SectionType type) {
        Iterator<Map.Entry<String, Boolean>> entries = null;
        int availableSeats = 0;

        if (type == SectionType.BUSINESS)
            entries = businessSeatList.entrySet().iterator();
        else if (type == SectionType.ECONOMY)
            entries = economySeatList.entrySet().iterator();

        if (entries != null) {
            while (entries.hasNext()) {
                Map.Entry<String, Boolean> entry = entries.next();

                if (!entry.getValue()) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }

    public String reserveSeat(SectionType type) {
        Iterator<Map.Entry<String, Boolean>> entries = null;

        if (type == SectionType.BUSINESS) {
            entries = businessSeatList.entrySet().iterator();
        } else if (type == SectionType.ECONOMY) {
            entries = economySeatList.entrySet().iterator();
        }

        if (entries != null) {
            while (entries.hasNext()) {
                Map.Entry<String, Boolean> entry = entries.next();

                // if seat is empty (false), set to true
                if (!entry.getValue()) {
                    entry.setValue(true);
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    public boolean unreserveSeat(String seat) {
        Iterator<Map.Entry<String, Boolean>> entries = businessSeatList.entrySet().iterator();

        for (int i = 1; i <= 2; i++) { //loop twice for each list
            while (entries.hasNext()) {
                Map.Entry<String, Boolean> entry = entries.next();

                if (entry.getKey().equals(seat)) {
                    entry.setValue(false);
                    return true;
                }
            }
            if (i == 1)
                entries = economySeatList.entrySet().iterator();
        }

        return false;
    }

    public String getDestination() {
        return destination;
    }
}
