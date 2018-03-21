package se.lexicon.ui;

import se.lexicon.model.airline.AirlineManager;
import se.lexicon.model.airline.Airplane;
import se.lexicon.ui.login.types.UserType;

import java.util.Scanner;

public class SystemUI {

	private UserType user = UserType.Customer; //default login
    private static AirlineManager manager = new AirlineManager();

    public SystemUI(){
        SystemFunctionality.setManager(manager);
        manager.addPlane(new Airplane("flightnr1", 3));
        manager.addPlane(new Airplane("flightnr2", 200));
    }

	public void start() {
		try(Scanner scanner = new Scanner(System.in)) {
			boolean isRunning = true;

			SystemFunctionality.printWelcomeScreen();
			SystemFunctionality.printLogin(user);

			do {
				try {
					SystemFunctionality.user.printMenu();
					SystemFunctionality.printSelect();

					String keyboard = scanner.next().toLowerCase();
					System.out.println();

					switch (keyboard) {
						case "1":
						    SystemFunctionality.user.createReservation();
							break;
						case "2":

							break;
						case "q":
							SystemFunctionality.printQuit(user);
							isRunning = false;
							break;
						case "su":
							startSuperUser();
							user = UserType.Customer;
							SystemFunctionality.printLogin(user);
							break;
						default:
							System.out.println(keyboard + " is not a valid option. Please try again.");
					}

					// Catch any and all program-specific exceptions here to de-clutter your switch-case
					// in case of checked and/or custom exceptions.
				} catch (Exception e) {
					e.printStackTrace();
				    System.out.println("Exception caught in inner try : " + e.getMessage());
					isRunning = false;
				}
			} while (isRunning);
		} catch (Exception e) {
			System.out.println("Exception caught in outer try : " + e.getMessage());
		} finally {
			// Any tasks needed for cleaning up/saving/etc should be performed here.
			// The scanner auto-closes so don't worry about that.
		}
	}

	private void startSuperUser() {
		boolean isRunning = true;
		user = UserType.SuperUser;
		Scanner scanner = new Scanner(System.in);

		SystemFunctionality.printLogin(UserType.SuperUser);

		do{
			SystemFunctionality.superUser.printMenu();
			SystemFunctionality.printSelect();

			String keyboard = scanner.next().toLowerCase();
			System.out.println();

			switch (keyboard) {
				case "1":
					break;
				case "q":
					SystemFunctionality.printQuit(UserType.SuperUser);
					isRunning = false;
					break;
				default:
					System.out.println(keyboard + " is not a valid option. Please try again.");
			}
		} while (isRunning);
	}
}
