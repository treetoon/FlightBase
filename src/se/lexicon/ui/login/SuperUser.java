package se.lexicon.ui.login;

import se.lexicon.model.airline.AirlineManager;
import se.lexicon.model.airline.Airplane;

import java.util.Scanner;

public final class SuperUser {

    private static AirlineManager manager = null;

    public SuperUser(AirlineManager mgr) {
        manager = mgr;
    }

    public void printMenu() {

        //TILLFÄLLIGT KOD. TA BORT!!
        addSeat();

        System.out.println("1. Add Aeroplane");
        System.out.println("2. Remove Aeroplane");
        System.out.println("3. Show Airline Profit");
        System.out.println("Q. Quit SuperUser" + '\n');
    }

    public void Menu() {

    }

    public void createAirplane() {
        //input flyg info
        //manager.addPlane(new Airplane(0));
    }

    public void addSeat() {
        Scanner scanner=new Scanner(System.in);
        int index=1;

        Airplane currentAirplane=null;

        for (Airplane plane : manager.getPlanesList()) {
            System.out.println("(" + index + ")" + " Flight " + plane.getFlightNr() + ": " + " Destination " + plane.getDestination());
            index++;
        }
        boolean loop = true;

        do {
            System.out.print("Choose airplane: ");
            int airplaneChosenIndex = scanner.nextInt();
            int flightNr=manager.getPlane(airplaneChosenIndex-1).getFlightNr();

            currentAirplane=manager.getPlaneByFlightNr(flightNr);

            if (currentAirplane != null) {
                System.out.println("Flight " + flightNr + " chosen...");
                loop = false;
            } else {
                System.out.println("Airplane doesn't exist, can you even read?");
            }
        } while (loop);

        manager.addSeat();




    }

    public void printAirlineProfit() {
        System.out.println("---Airline Info---");
        System.out.println("Income: " + manager.getIncome());
        System.out.println("Profit: " + manager.getProfit() + '\n');
    }
}
