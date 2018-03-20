package se.lexicon.ui;

import se.lexicon.model.airline.AirlineManager;
import se.lexicon.ui.login.SuperUser;
import se.lexicon.ui.login.User;
import se.lexicon.ui.login.types.UserType;

public final class SystemFunctionality {
    private static AirlineManager manager = null;
    public static User user = null;
    public static SuperUser superUser = null;

    private SystemFunctionality(){} //block instantiation

    public static void setManager(AirlineManager mgr){
        manager = mgr;
        user = new User(manager);
        superUser = new SuperUser(manager);
    }

    public static void printWelcomeScreen(){
        System.out.println("-------------------------------------------");
        System.out.println("--Welcome to Flight Manager 0.1 pre-alpha--");
        System.out.println("-------------------------------------------" + '\n');
        System.out.println("㋡ ☺ ☹ ☻ 〠 シ ヅ Ü 〲 〴 ϡ ﭢ ت〠 シ ッ ツ ");
        System.out.println("㋡  Ü 〠 シ 〲 〴 ϡ  ヅﭢﭢ ت");
        System.out.println("☹ ☻ 〠 シ ヅ Ü㋡ ㋛ ϡ ﭢ  ☺  〲 〴ت" + '\n');
    }

    public static void printSelect(){
        System.out.print("Select: ");
    }

    public static void printLogin(UserType user){
        System.out.println("-------------------------------------------");
        System.out.println("---> You are now in logged in as " + user);
        System.out.println("-------------------------------------------" + '\n');
    }

    public static void printQuit(UserType user){
        System.out.println("Quitting " + user + "..." + '\n');
    }
}
