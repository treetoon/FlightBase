package se.lexicon.ui;

import se.lexicon.model.airline.AirlineManager;
import se.lexicon.ui.login.SuperUser;
import se.lexicon.ui.login.User;
import se.lexicon.ui.login.types.UserType;

import static java.lang.System.out;

public final class SystemFunctionality {
    private static AirlineManager manager = new AirlineManager();
    public static User user;
    public static SuperUser superUser;

    static { //initialization list
        user = new User(manager);
        superUser = new SuperUser(manager);
    }

    private SystemFunctionality(){} //block instantiation

    public static boolean setManager(AirlineManager mgr){
        if(mgr != null) {
            manager = mgr;
            user = new User(manager);
            superUser = new SuperUser(manager);
            return true;
        }
        return false;
    }

    public static void printWelcomeScreen(){
        out.println("-------------------------------------------");
        out.println("--Welcome to Flight Manager 0.1 pre-alpha--");
        out.println("-------------------------------------------" + '\n');
        out.println("㋡ ☺ ☹ ☻ 〠 シ ヅ Ü 〲 〴 ϡ ﭢ ت〠 シ ッ ツ ");
        out.println("㋡  Ü 〠 シ 〲 〴 ϡ  ヅﭢﭢ ت");
        out.println("☹ ☻ 〠 シ ヅ Ü㋡ ㋛ ϡ ﭢ  ☺  〲 〴ت" + '\n');
    }

    public static void printSelect(){
        out.print("Select: ");
    }

    public static void printLogin(UserType user){
        out.println("-------------------------------------------");
        out.println("---> You are now in logged in as " + user);
        out.println("-------------------------------------------" + '\n');
    }

    public static void printQuit(UserType user){
        out.println("Quitting " + user + "..." + '\n');
    }
}
