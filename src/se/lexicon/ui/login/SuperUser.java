package se.lexicon.ui.login;

import se.lexicon.model.airline.AirlineManager;
import se.lexicon.model.airline.Airplane;

import java.util.Scanner;

public final class SuperUser {

    private static AirlineManager manager = null;
    private Scanner scanner = new Scanner(System.in);

    public SuperUser(AirlineManager mgr) {
        manager = mgr;
    }

    public void printMenu() {

        //TILLFÄLLIGT KOD. TA BORT!!
        addSeat();

        System.out.println("1. Add Aeroplane");
        System.out.println("2. Remove Aeroplane");
        System.out.println("3. Add Seat To a Plane");
        System.out.println("4. Remove Seat To a Plane");
        System.out.println("5. Show Airline Profit");
        System.out.println("Q. Quit SuperUser" + '\n');
    }

    public boolean createAirplane() {
        System.out.println("How many seats should the aeroplane have?");
        int numOfSeats = scanner.nextInt();

        System.out.println("What should the business section price be?");
        int businessSectionPrice = scanner.nextInt();

        System.out.println("What should the economy section price be?");
        int economySectionPrice = scanner.nextInt();

        System.out.println("What's the current destination?");
        String destination = scanner.next();

        if(manager.addPlane(new Airplane(numOfSeats, businessSectionPrice, economySectionPrice, destination))){
            System.out.println("Aeroplane added!" + '\n');
            return true;
        }else{
            System.out.println("Could not add..." + '\n');
            return false;
        }
    }

    public boolean removeAirplane() {
        for (Airplane plane : manager.getPlanesList()) {
            System.out.println("---Flight " + plane.getFlightNr() + "---");
            System.out.println("Current Destination: " + plane.getDestination() + '\n');
        }

        System.out.println("Which aeroplane would you like to remove?" + '\n' + "Enter Flight Number: ");

        if(manager.removePlane(scanner.nextInt())){
            System.out.println("Aeroplane removed!" + '\n');
            return true;
        }else{
            System.out.println("Could not remove..." + '\n');
            return false;
        }
    }

    public void addSeat() {

    }

    public void removeSeat() {

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
