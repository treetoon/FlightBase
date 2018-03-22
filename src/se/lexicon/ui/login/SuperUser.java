package se.lexicon.ui.login;

import se.lexicon.model.airline.AirlineManager;

public final class SuperUser {

    private static AirlineManager manager = null;

    public SuperUser(AirlineManager mgr) {
        manager = mgr;
    }

    public void printMenu() {
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

    public void printAirlineProfit() {
        System.out.println("---Airline Info---");
        System.out.println("Income: " + manager.getIncome());
        System.out.println("Profit: " + manager.getProfit() + '\n');
    }
}
