package se.lexicon.ui.login;

import se.lexicon.model.airline.AirlineManager;

public final class SuperUser {

    private static AirlineManager manager = null;

    public SuperUser(AirlineManager mgr){
        manager = mgr;
    }

    public void printMenu(){
        //System.out.println("1. X");
        System.out.println("Q. Quit SuperUser" + '\n');
    }
}
