package se.lexicon.ui.login;

import se.lexicon.model.airline.AirlineManager;
import se.lexicon.model.airline.Airplane;
import se.lexicon.model.airline.types.SectionType;
import se.lexicon.ui.login.types.UserType;

import java.util.Scanner;

public final class User {

    private static AirlineManager manager = null;

    public User(AirlineManager mgr){
        manager = mgr;
    }

    public void printMenu(){
        System.out.println("1. Create Reservation");
        System.out.println("2. Edit Reservation");
        System.out.println("Q. Quit" + '\n');
    }

    public void createReservation(AirlineManager manager){
        Scanner scanner = new Scanner(System.in);

//        System.out.print("Enter First Name: ");
//        String firstName = scanner.next().toLowerCase();
//
//        System.out.print("Enter Surname: ");
//        String surName = scanner.next().toLowerCase();
//
//        System.out.print("Enter address: ");
//        String address = scanner.next().toLowerCase();
//
//        System.out.print("Enter telephone number: ");
//        String phoneNr = scanner.next().toLowerCase();
//
//        System.out.print("Business or Economy Travel?: ");
//        String section = scanner.next().toLowerCase();


        int i=1;
        for (Airplane plane : manager.getPlanesList()) {
            System.out.println("Flight (" + i + "): "  + plane.getFlightNr());

            System.out.println("Available seats: Economy section: " + plane.numberOfAvailableEconomySeats() +
            " Business section: " + plane.numberOfAvailableBusinessSeats());
            System.out.println("----------------------------------------------------------------");
            i++;
        }

        System.out.print("Choose airplane: ");
        int airplaneIndex = scanner.nextInt();

        //sätter sectiontype tillfälligt
        //index-1 för att användaren anger index som är 1 mer än i verkligheten..
        String seatNr=manager.getPlane(airplaneIndex-1).reserveSeat(SectionType.BUSINESS);

        System.out.println(seatNr);

        while (seatNr==null) {
            System.out.println("Failed to assign seat in chosen section. Choose another section (1) or cancel reservation (2)?");
            int selection = scanner.nextInt();

            if (selection==1) {
                System.out.println("Business or Economy Travel?");
                int selectionSection = scanner.nextInt();


                manager.getPlane(airplaneIndex-1).reserveSeat(SectionType.BUSINESS);
            }
            else if (selection==2) {

            }
        }


//        private String reservationNumber;
//        private String seatNumber;
//        private String flightNumber;
//        private SectionType sectionType;
//        private List<Food> foodList;
//        private Customer customer;


    }
}
