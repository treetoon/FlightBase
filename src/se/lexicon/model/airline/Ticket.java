package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;

public class Ticket {
    private String seatNumber;
    private String flightNumber;
    private int price;
    private SectionType sectionType;

    public Ticket(String seatNumber, String flightNumber, int price, SectionType sectionType) {
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.price = price;
        this.sectionType = sectionType;
    }


}
