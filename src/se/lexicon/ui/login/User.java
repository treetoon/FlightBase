package se.lexicon.ui.login;

import se.lexicon.model.airline.*;
import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.Food;

import java.sql.SQLOutput;
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

//        System.out.print("Enter first name: ");
//        String firstName = scanner.next().toLowerCase();
//
//        System.out.print("Enter last name: ");
//        String lastName = scanner.next().toLowerCase();
//
//        System.out.print("Enter address: ");
//        String address = scanner.next().toLowerCase();
//
//        System.out.print("Enter telephone number: ");
//        String phoneNr = scanner.next().toLowerCase();


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
        int flightNr = manager.getPlane(airplaneIndex - 1).getFlightNr();
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


        //Hur sätts price??
        int price = 20000;    //tillfälligt!
        Ticket ticket = new Ticket(seatNr, flightNr, price, Enum.valueOf(SectionType.class, sectionType));

        Reservation reservation = new Reservation(customer, ticket);

        //ändra
        System.out.println("Ticket with seat number " + seatNr + " created");
        System.out.println("---------------------------------------------------------------");

        createFoodReservation(scanner, reservation, Enum.valueOf(SectionType.class, sectionType), price);
    }

    public void createFoodReservation(Scanner scanner, Reservation reservation, SectionType sectionType, int price) {
        boolean continueLooping = true;

        do {
            System.out.println("Available food items from menu:");

            int foodNr = 1;

            if (sectionType == SectionType.BUSINESS) {
                for (Food food : manager.getFoodManager().getBusinessFoodList()) {
                    System.out.println("(" + foodNr + ")" + "Food name: " + food.getName() + "\t" + " Price: " + food.getPrice());
                    foodNr++;
                }
            } else if (sectionType == SectionType.ECONOMY) {
                for (Food food : manager.getFoodManager().getEconomyFoodList()) {
                    System.out.println("(" + foodNr + ")" + "Food name: " + food.getName() + "\t" + " Price: " + food.getPrice());
                    foodNr++;
                }
            }

            System.out.println("Which food item would you like?");

            int foodChoice = scanner.nextInt();

            Food food = null;

            if (sectionType == SectionType.BUSINESS) {
                food = manager.getFoodManager().getBusinessFoodList().get(foodChoice - 1);
                reservation.getFoodList().add(food);
            } else if (sectionType == SectionType.ECONOMY) {
                food = manager.getFoodManager().getEconomyFoodList().get(foodChoice - 1);
                reservation.getFoodList().add(food);
            }

            System.out.println(food.getName() + " added!");

            System.out.println("CURRENT FOOD ORDER: ");

            int totalCost = 0;

            for (Food foodItem : reservation.getFoodList()) {
                System.out.println("Food name: " + foodItem.getName());
                totalCost += foodItem.getPrice();
            }

            System.out.println("Total: " + totalCost);

            System.out.println("------------------------------------------------------------");

            System.out.println("Would you like to add more food items (y/n) ");

            String answer = scanner.next();

            if (answer.toLowerCase().equals("y")) {
                continue;
            } else if (answer.toLowerCase().equals("n")) {
                continueLooping = false;
                System.out.println("Finished adding food items");
            }
        } while (continueLooping);

        //VAD HÄNDER EFTER DETTA???

    }

    public void editReservation() {



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
}
