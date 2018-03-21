package se.lexicon.ui.login;

import se.lexicon.model.airline.*;
import se.lexicon.model.airline.types.SectionType;

import java.util.Scanner;

public final class User {

    private static AirlineManager manager = null;

    public User(AirlineManager mgr) {
        manager = mgr;
    }

    public void printMenu() {
        System.out.println("1. Create Reservation");
        System.out.println("2. Edit Reservation");
        System.out.println("Q. Quit" + '\n');
    }

    public void createReservation() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = scanner.next().toLowerCase();

        System.out.print("Enter last name: ");
        String lastName = scanner.next().toLowerCase();

        System.out.print("Enter address: ");
        String address = scanner.next().toLowerCase();

        System.out.print("Enter telephone number: ");
        String phoneNr = scanner.next().toLowerCase();


        String sectionType = chooseSectionType(scanner);

        int i = 1;
        for (Airplane plane : manager.getPlanesList()) {
            System.out.println("Flight (" + i + "): " + plane.getFlightNr());

            System.out.println("Available seats: Business section: " + plane.numberOfAvailableBusinessSeats() +
                    " Economy section: " + plane.numberOfAvailableEconomySeats());
            System.out.println("----------------------------------------------------------------");
            i++;
        }

        System.out.print("Choose airplane: ");
        int airplaneIndex = scanner.nextInt();
        String flightNr = manager.getPlane(airplaneIndex - 1).getFlightNr();
        System.out.println("Flight " + flightNr + " chosen");

        String seatNr = "";

        do {
            //index-1 för att användaren anger index som är 1 mer än i verkligheten..
            seatNr = manager.getPlane(airplaneIndex - 1).reserveSeat(Enum.valueOf(SectionType.class, sectionType));

            if (seatNr != null) {
                break;
            }

            System.out.print("Failed to assign seat in chosen section. Choose another section (1) or cancel reservation (2)?");
            int selection = scanner.nextInt();

            boolean continueLoop = true;

            do {
                switch (selection) {
                    case 1:
                        sectionType = chooseSectionType(scanner);
                        seatNr = manager.getPlane(airplaneIndex - 1).reserveSeat(Enum.valueOf(SectionType.class, sectionType));
                        continueLoop = false;
                        break;
                    case 2:
                        System.out.println("Canceled reservation");
                        return;
                    default:
                        System.out.println("Wrong choice. Try again");
                        break;
                }
            } while (continueLoop);

        } while (seatNr == null);

        Customer customer = new Customer("firstname", "lastName", "address", "phoneNr");
        //Customer customer=new Customer(firstName, lastName, address, phoneNr);

        //Ticket ticket=new Ticket();

        //Reservation reservation = new Reservation(seatNr, flightNr, Enum.valueOf(SectionType.class, sectionType),
               // null, customer);

        //createFoodReservation(scanner, reservation);
    }

    public String chooseSectionType(Scanner scanner) {
        String sectionType = "";
        boolean continueLooping = true;

        do {
            System.out.print("Business(1) or Economy(2) Travel?: ");
            int sectionTypeChoice = scanner.nextInt();

            switch (sectionTypeChoice) {
                case 1:
                    sectionType = SectionType.BUSINESS.toString();
                    continueLooping = false;
                    break;
                case 2:
                    sectionType = SectionType.ECONOMY.toString();
                    continueLooping = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again!");
                    break;
            }
        } while (continueLooping);

        return sectionType;
    }


    public void createFoodReservation(Scanner scanner, Reservation reservation) {
    }
}
