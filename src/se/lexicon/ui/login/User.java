package se.lexicon.ui.login;

import se.lexicon.exception.AirlineException;
import se.lexicon.model.airline.*;
import se.lexicon.model.airline.types.SectionType;
import se.lexicon.model.food.Food;

import java.util.List;
import java.util.Scanner;

public final class User {
    private static AirlineManager manager = null;
    private Scanner scanner = new Scanner(System.in);

    private int reservationNr = 0;
    private int flightNr = 0;
    private int airplaneIndex = 0;
    private int totalFoodCost = 0;

    private SectionType sectionType = null;
    private String firstName = null, lastName = null,
            address = null, phoneNr = null;
    private String seatNr = null;

    public User(AirlineManager mgr) {
        if (mgr != null)
            manager = mgr;
    }

    public void printMenu() {
        System.out.println("1. Create Reservation");
        System.out.println("2. Edit Reservation");
        System.out.println("3. Delete Reservation");
        System.out.println("SU. Login as SuperUser-->");
        System.out.println("Q. Quit" + '\n');
    }

    public void createReservation() {
        chooseSectionType();
        createCustomer();

        printAllPlanesAndSeats();

        chooseAirplane();
        chooseSeat();

        reservationNr = manager.createReservation(firstName, lastName, address, phoneNr,
                seatNr, flightNr, sectionType);

        System.out.println("Ticket with seat number " + seatNr + " created");
        System.out.println("-------------------------------------------");

        System.out.println("Would you like to order food (y/n)?");
        boolean ask = false;

        do {
            String answer = scanner.next();

            switch (answer) {
                case "Y":
                case "y":
                    ask=false;
                    createFoodReservation();
                    break;
                case "N":
                case "n":
                    ask = false;
                    manager.getReservationsList().get(reservationNr - 1).calculateTotalPrice();     //Sets total price
                    printReceipt();
                    break;
                default:
                    System.out.println("Please write y or n...");
                    ask=true;
                    break;
            }
        } while (ask);
    }

    public void deleteReservation() {
        System.out.println("Which reservation would you like to delete? Input reservation number");
        reservationNr = scanner.nextInt();

        if (reservationNr == 0) {
            System.out.println("Canceling...");
            return;
        }

        if (manager.deleteReservation(reservationNr)) {
            System.out.println("Delete reservation successful!");
        } else {
            System.out.println("Delete reservation failed. Try again!");
            deleteReservation();
        }
    }

    public void editReservation() {
        System.out.println("Which reservation would you like to edit? Input reservation number");
        reservationNr = scanner.nextInt();

        if (reservationNr == 0) {
            System.out.println("Canceling...");
            return;
        }

        //Checks if reservation number is valid
        if (reservationNr > manager.getReservationsList().size()) {
            System.out.println("Invalid reservation number. Try again!");
            editReservation();
        }

        if (manager.getReservationsList().get(reservationNr - 1) != null) {
            sectionType = manager.getReservationsList().get(reservationNr - 1).getTicket().getSectionType();

            System.out.println("Editing current food order...");

            boolean ask = false;

            do {
                System.out.println("Would you like to add (1) or remove (2) food items?");
                int selection = scanner.nextInt();

                if (selection == 1) {
                    addFood();
                } else if (selection == 2) {
                    removeFood();
                } else {
                    System.out.println("Wrong choice. Try again!");
                    ask = true;
                }
            } while (ask);

        } else {
            System.out.println("No reservation found. Try again!");
            editReservation();
        }
    }

    //Private Methods

    private void createFoodReservation() {
        boolean continueLooping = true;
        boolean ask = true;

        do {
            totalFoodCost = 0;
            printFood();

            System.out.println("Which food item would you like?");
            int foodChoice = scanner.nextInt();

            Food food = null;

            if (sectionType == SectionType.BUSINESS) {
                int size = manager.getFoodManager().getBusinessFoodList().size();

                if (foodChoice <= size) {
                    food = manager.getFoodManager().getBusinessFoodList().get(foodChoice - 1);
                    manager.getReservationsList().get(reservationNr - 1).addFoodItem(food);
                } else {
                    System.out.println("Wrong input. Try again!");
                    continue;
                }
            } else if (sectionType == SectionType.ECONOMY) {
                int size = manager.getFoodManager().getEconomyFoodList().size();

                if (foodChoice <= size) {
                    food = manager.getFoodManager().getEconomyFoodList().get(foodChoice - 1);
                    manager.getReservationsList().get(reservationNr - 1).addFoodItem(food);
                } else {
                    System.out.println("Wrong input. Try again!");
                    continue;
                }
            }

            if (food != null)
                System.out.println(food.getName() + " added!");

            System.out.println("-------------------------------------------");
            System.out.println("Current Food Order: ");

            for (Food foodItem : manager.getReservationsList().get(reservationNr - 1).getFoodList()) {
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
                        manager.getReservationsList().get(reservationNr - 1).calculateTotalPrice();     //Sets total price
                        printReceipt();
                        break;
                    default:
                        System.out.println("Please write y or n...");
                        break;
                }
            } while (ask);
        } while (continueLooping);
    }

    private void addFood() {
        printFoodOrder();

        createFoodReservation();
    }

    private void removeFood() {
        printFoodOrder();

        boolean continueLooping = true;
        boolean ask = true;

        do {
            totalFoodCost = 0;

            System.out.println("Which food item would you like to remove?");
            int foodChoice = scanner.nextInt();

            if (foodChoice == 0) {
                System.out.println("Canceling...");
                return;
            }

            String foodName = manager.getReservationsList().get(reservationNr - 1).removeFoodItem(foodChoice - 1);

            if (foodName != null) {
                System.out.println(foodName + " removed");

                manager.getReservationsList().get(reservationNr - 1).calculateTotalPrice();     //Sets total price

                //If the food list in the reservation is empty - return
                if (manager.getReservationsList().get(reservationNr - 1).getFoodList().size() == 0) {
                    System.out.println("All food items removed!");
                    return;
                }

                printFoodOrder();

                System.out.println("Would you like to remove more food items? (y/n) ");

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
                            System.out.println("Finished removing food items");
                            manager.getReservationsList().get(reservationNr - 1).calculateTotalPrice();     //Sets total price
                            printReceipt();
                            break;
                        default:
                            System.out.println("Please write y or n...");
                            break;
                    }
                } while (ask);
            } else {
                System.out.println("Remove food item failed!");
            }
        } while (continueLooping);
    }

    private void printFoodOrder() {
        System.out.println("-------------------------------------------");
        System.out.println("Current food order: ");

        int totalFoodCost = 0;
        int i = 1;

        for (Food foodItem : manager.getReservationsList().get(reservationNr - 1).getFoodList()) {
            System.out.println(i + ". Food name: " + foodItem.getName());
            totalFoodCost += foodItem.getPrice();
            i++;
        }

        System.out.println("Total: " + totalFoodCost);
        System.out.println("-------------------------------------------");
    }

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
            System.out.println("Destination: " + plane.getDestination());
            System.out.println("Available seats--> " + '\n' +
                    "Business section: " + plane.numberOfAvailableBusinessSeats() + '\n' +
                    "Economy section: " + plane.numberOfAvailableEconomySeats());
            System.out.println("-------------------------------------------");
        }
    }

    private void chooseAirplane() {
        boolean loop = true;

        do {
            System.out.print("Choose airplane: ");
            airplaneIndex = scanner.nextInt();

            if (manager.getPlaneByFlightNr(airplaneIndex) != null) {
                flightNr = manager.getPlaneByFlightNr(airplaneIndex).getFlightNr();
                System.out.println("Flight " + flightNr + " chosen...");
                loop = false;
            } else {
                System.out.println("Airplane doesn't exist, can you even read?");
            }
        } while (loop);
    }

    private void chooseSeat() {
        int selection = 0;

        do {
            seatNr = manager.getPlaneByFlightNr(airplaneIndex).reserveSeat(sectionType);

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
                        seatNr = manager.getPlaneByFlightNr(airplaneIndex).reserveSeat(sectionType);
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

    private void printReceipt() {
        System.out.println("-------------------------------------");
        System.out.println("Receipt for customer: " + manager.getReservationsList().get(reservationNr - 1).getCustomer().getFirstName() +
                " " + manager.getReservationsList().get(reservationNr - 1).getCustomer().getLastName());
        System.out.println("Ticket price:\t" + manager.getReservationsList().get(reservationNr - 1).getTicket().getPrice());
        System.out.println("Food price: \t" + manager.getReservationsList().get(reservationNr - 1).calculateTotalFoodPrice());
        System.out.println("Total price: \t" + manager.getReservationsList().get(reservationNr - 1).calculateTotalPrice());
        System.out.println("-------------------------------------");
    }

    private void printFood() {
        System.out.println("Available food items from menu:");

        int foodNr = 1;
        List<Food> foodList = null;

        if (sectionType == SectionType.BUSINESS) {
            foodList = manager.getFoodManager().getBusinessFoodList();
        } else if (sectionType == SectionType.ECONOMY) {
            foodList = manager.getFoodManager().getEconomyFoodList();
        } else {
            return;
        }

        for (Food food : foodList) {
            System.out.println("(" + foodNr + ")" + "Food name: " +
                    food.getName() + "\t" + " Price: " + food.getPrice());
            foodNr++;
        }
    }
}