package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Airplane {

    private String flightNr;

    private Map<String, Boolean> businessSeatList;
    private Map<String, Boolean> economySeatList;

    public Airplane(String flightNr, int size) {
        this.flightNr=flightNr;

        businessSeatList=new TreeMap<>();
        economySeatList=new TreeMap<>();

        //6 platser per rad (A-F)
        //A-C == BUSINESS
        //D-F == ECONOMY

        int numberOfRows=size/6;
        int remainingSeats=size%6;

        System.out.println("rows: " + numberOfRows);
        System.out.println("resten: " + remainingSeats);

        for (int i=1; i<=numberOfRows; i++) {
            businessSeatList.put(i+ "A", false);
            businessSeatList.put(i+ "B", false);
            businessSeatList.put(i+ "C", false);

            economySeatList.put(i+ "D", false);
            economySeatList.put(i+ "E", false);
            economySeatList.put(i+ "F", false);

        }

        //If there are remaining seats to be assigned (a last row)
        if (remainingSeats > 0) {
            char seatLetter = 'A';
            int lastRowNumber = numberOfRows + 1;

            for (int i = 1; i <= remainingSeats; i++) {
                if (seatLetter == 'A' || seatLetter == 'B' | seatLetter == 'C') {
                    String s = "" + lastRowNumber + seatLetter;
                    //System.out.println("business " + s);
                    businessSeatList.put(s, false);
                } else {
                    String s = "" + lastRowNumber + seatLetter;
                    //System.out.println("economy" + s);
                    economySeatList.put(s, false);
                }
                seatLetter++;
            }
        }

//        System.out.println("Businesslistan: ");
//        for (Map.Entry<String, Boolean> entry : businessSeatList.entrySet()) {
//            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//        }
//
//        System.out.println("Economylistan: ");
//        for (Map.Entry<String, Boolean> entry : economySeatList.entrySet()) {
//            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
//        }


    }

    public String reserveSeat(SectionType type) {

        if (type==SectionType.BUSINESS) {
            Iterator<Map.Entry<String, Boolean>> entries = businessSeatList.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Boolean> entry = entries.next();

                // if seat is empty (false), set to true
                if (entry.getValue()==false) {
                    entry.setValue(true);
                    return entry.getKey();

                }
            }
        }
        else if (type==SectionType.ECONOMY) {
            Iterator<Map.Entry<String, Boolean>> entries = economySeatList.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Boolean> entry = entries.next();

                if (entry.getValue()==false) {
                    entry.setValue(true);
                    return entry.getKey();
                }
            }
        }

        return null;
    }

    //kan SectionType vara med som parameter?
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

    public void addSeat() {}
    public void removeSeat() {}

}
