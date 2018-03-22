package se.lexicon.model.airline;

import se.lexicon.model.airline.types.SectionType;

public class Ticket {
    private String seatNumber;
    private int flightNumber;
    private int price;
    private SectionType sectionType;

    public Ticket(String seatNumber, int flightNumber, int price, SectionType sectionType) {
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.price = price;
        this.sectionType = sectionType;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public int getPrice() {
        return price;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "seatNumber='" + seatNumber + '\'' +
                ", flightNumber=" + flightNumber +
                ", price=" + price +
                ", sectionType=" + sectionType +
                '}';
    }
}
