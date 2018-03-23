package se.lexicon.ui;

import se.lexicon.ui.login.types.UserType;

import java.util.Scanner;

public class SystemUI {

	private UserType user = UserType.Customer; //default login

    public SystemUI()
	{
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
							SystemFunctionality.user.editReservation();
							break;
						case "3":
							SystemFunctionality.user.deleteReservation();
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

				} catch (Exception e) {
					e.printStackTrace();
				    System.out.println("Exception caught in inner try : " + e.getMessage());
					isRunning = false;
				}
			} while (isRunning);
		} catch (Exception e) {
			System.out.println("Exception caught in outer try : " + e.getMessage());
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
					SystemFunctionality.superUser.createAirplane();
					break;
				case "2":
					SystemFunctionality.superUser.removeAirplane();
					break;
				case "3":
					SystemFunctionality.superUser.addSeat();
					break;
				case "4":
					SystemFunctionality.superUser.removeSeat();
					break;
				case "5":
					SystemFunctionality.superUser.printAirlineProfit();
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
