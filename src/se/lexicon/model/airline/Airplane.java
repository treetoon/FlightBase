package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;

import java.util.*;

public class Airplane {
    private int flightNr;
    private static int flightCounter = 0;

    private Map<String, Boolean> businessSeatList;
    private Map<String, Boolean> economySeatList;

    public Airplane(int numOfSeats) {
        flightCounter++;
        flightNr=flightCounter;

        businessSeatList = new LinkedHashMap<>();
        economySeatList = new LinkedHashMap<>();

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
                if (seatLetter == 'A' || seatLetter == 'B' | seatLetter == 'C') {
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

    public int getFlightNr() {
        return flightNr;
    }

    public int totalSeatsOfAirplane() {
        return (this.businessSeatList.size() + this.economySeatList.size());
    }

    public int numberOfAvailableBusinessSeats() {
        int availableSeats = 0;

        Iterator<Map.Entry<String, Boolean>> entries = businessSeatList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Boolean> entry = entries.next();

            if (entry.getValue() == false) {
                availableSeats++;
            }
        }
        return availableSeats;
    }

    public int numberOfAvailableEconomySeats() {
        int availableSeats = 0;

        Iterator<Map.Entry<String, Boolean>> entries = economySeatList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Boolean> entry = entries.next();

            if (entry.getValue() == false) {
                availableSeats++;
            }
        }

        return availableSeats;
    }

    public int numberOfAvailableSeats(SectionType sectionType) {
        int availableSeats = 0;

        if (sectionType==SectionType.BUSINESS) {

            Iterator<Map.Entry<String, Boolean>> entries = economySeatList.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Boolean> entry = entries.next();

                if (entry.getValue() == false) {
                    availableSeats++;
                }
            }
        } else if (sectionType==SectionType.ECONOMY){

        }

        return availableSeats;
    }

    public String reserveSeat(SectionType type) {

        if (type == SectionType.BUSINESS) {
            Iterator<Map.Entry<String, Boolean>> entries = businessSeatList.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Boolean> entry = entries.next();

                // if seat is empty (false), set to true
                if (entry.getValue() == false) {
                    entry.setValue(true);
                    return entry.getKey();

                }
            }
        } else if (type == SectionType.ECONOMY) {
            Iterator<Map.Entry<String, Boolean>> entries = economySeatList.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Boolean> entry = entries.next();

                if (entry.getValue() == false) {
                    entry.setValue(true);
                    return entry.getKey();
                }
            }
        }

        return null;
    }

    public void unreserveSeat(String seat) {
        Iterator<Map.Entry<String, Boolean>> entries = businessSeatList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Boolean> entry = entries.next();

            if (entry.getKey().equals(seat)) {
                entry.setValue(false);
            }
        }

        entries = economySeatList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Boolean> entry = entries.next();

            if (entry.getKey().equals(seat)) {
                entry.setValue(false);
            }
        }

    }

    public void addSeat() {
    }

    public void removeSeat() {
    }

}
