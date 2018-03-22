package se.lexicon.ui.login;

import se.lexicon.model.airline.*;
import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.Food;

import java.util.List;
import java.util.Scanner;

public final class User {
    private static AirlineManager manager = null;
    private Scanner scanner = new Scanner(System.in);

    private int reservationNr = 0;
    private SectionType sectionType = null;
    private String firstName = null, lastName = null,
                    address = null, phoneNr = null;
    private int airplaneIndex = 0;
    private int flightNr = 0;
    private String seatNr = null;
    private int totalFoodCost = 0;

    public User(AirlineManager mgr) {
        manager = mgr;
    }

    public void printMenu() {
        System.out.println("1. Create Reservation");
        System.out.println("2. Edit Reservation");
        System.out.println("Q. Quit" + '\n');
    }

    public void createReservation() {
        int price = 2000;

        chooseSectionType();
        createCustomer();

        printAllPlanesAndSeats();

        chooseAirplane();
        chooseSeat();

        reservationNr = manager.createReservation(firstName, lastName, address, phoneNr,
                seatNr, flightNr, price, sectionType);

        System.out.println("Ticket with seat number " + seatNr + " created");
        System.out.println("-------------------------------------------");

        createFoodReservation();
    }

    public void editReservation() {

    }

    /*
    ---------------------------------------------
    ---------------------------------------------
    Private Methods
    ---------------------------------------------
    ---------------------------------------------
     */

    private void chooseSectionType() {
        boolean continueLooping = true;

        do {
            System.out.print("Business(1) or Economy(2) Travel?: ");
            int sectionTypeChoice = scanner.nextInt();
            System.out.println();

            switch (sectionTypeChoice) {
                case 1:
                    sectionType = SectionType.BUSINESS;
                    continueLooping = false;
                    break;
                case 2:
                    sectionType = SectionType.ECONOMY;
                    continueLooping = false;
                    break;
                default:
                    System.out.println("Wrong choice. Try again!");
                    break;
            }
        } while (continueLooping);
    }

    private void createCustomer() {
        System.out.print("Enter first name: ");
        firstName = scanner.next().toLowerCase();

        System.out.print("Enter last name: ");
        lastName = scanner.next().toLowerCase();

        System.out.print("Enter address: ");
        address = scanner.next().toLowerCase();

        System.out.print("Enter telephone number: ");
        phoneNr = scanner.next().toLowerCase();
    }

    private void printAllPlanesAndSeats() {
        for (Airplane plane : manager.getPlanesList()) {
            System.out.println('\n' + "---Flight " + plane.getFlightNr() + "---");
            System.out.println("Available seats--> " + '\n' +
                    "Business section: " + plane.numberOfAvailableBusinessSeats() + '\n' +
                    "Economy section: " + plane.numberOfAvailableEconomySeats());
            System.out.println("-------------------------------------------");
        }
    }

    private void chooseAirplane(){
        boolean loop = true;

        do {
            System.out.print("Choose airplane: ");
            airplaneIndex = scanner.nextInt();

            if (manager.getPlane(airplaneIndex - 1) != null) {
                flightNr = manager.getPlane(airplaneIndex - 1).getFlightNr();

                System.out.println("Flight " + flightNr + " chosen...");
                loop = false;
            }else{
                System.out.println("Airplane doesn't exist, can you even read?");
            }
        }while (loop);
    }

    private void chooseSeat() {
        int selection = 0;

        do {
            //index-1 för att användaren anger index som är 1 mer än i verkligheten..
            seatNr = manager.getPlane(airplaneIndex - 1).reserveSeat(sectionType);

            if (seatNr != null) {
                break;
            }

            System.out.print("Failed to assign seat in chosen section. " +
                    "Choose another section (1) or cancel reservation (2)?");
            selection = scanner.nextInt();

            boolean continueLoop = true;

            do {
                switch (selection) {
                    case 1:
                        chooseSectionType();
                        seatNr = manager.getPlane(airplaneIndex - 1).reserveSeat(sectionType);
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
    }

    private void createFoodReservation() {
        boolean continueLooping = true;
        boolean ask = true;
        totalFoodCost = 0;

        do {
            printFood();

            System.out.println("Which food item would you like?");
            int foodChoice = scanner.nextInt();

            Food food = null;

            if (sectionType == SectionType.BUSINESS) {
                food = manager.getFoodManager().getBusinessFoodList().get(foodChoice - 1);
                manager.getReservationsList().get(reservationNr).add(food);
            } else if (sectionType == SectionType.ECONOMY) {
                food = manager.getFoodManager().getEconomyFoodList().get(foodChoice - 1);
                reservation.getFoodList().add(food);
            }

            if (food != null)
                System.out.println(food.getName() + " added!");

            System.out.println("CURRENT FOOD ORDER: ");

            for (Food foodItem : reservation.getFoodList()) {
                System.out.println("Food name: " + foodItem.getName());
                totalFoodCost += foodItem.getPrice();
            }

            System.out.println("Total: " + totalFoodCost);
            System.out.println("-------------------------------------------");
            System.out.println("Would you like to add more food items? (y/n) ");

            do {
                String answer = scanner.next();

                switch (answer) {
                    case "Y":
                    case "y":
                        ask = false;
                        break;
                    case "N":
                    case "n":
                        ask = false;
                        continueLooping = false;
                        System.out.println("Finished adding food items");
                        break;
                    default:
                        System.out.println("Please write y or n...");
                        break;
                }
            } while (ask);
        } while (continueLooping);
    }

    private void printFood(){
        System.out.println("Available food items from menu:");

        int foodNr = 1;
        List<Food> foodList = null;

        if (sectionType == SectionType.BUSINESS) {
            foodList = manager.getFoodManager().getBusinessFoodList();
        } else if (sectionType == SectionType.ECONOMY) {
            foodList = manager.getFoodManager().getEconomyFoodList();
        }else {
            return;
        }

        for (Food food : foodList) {
            System.out.println("(" + foodNr + ")" + "Food name: " +
                    food.getName() + "\t" + " Price: " + food.getPrice());
            foodNr++;
        }
    }
}