package se.lexicon.ui;

import java.util.Scanner;

public class SystemUI {

	public void start() {
		boolean isRunning = true;

		SystemPrint.welcomeScreen();

		try(Scanner scanner = new Scanner(System.in)) {
			do {
				try {
					SystemPrint.printMenu();

					System.out.println("User input : ");
					String keyboard = scanner.next();

					switch (keyboard) {
						case "1":
							System.out.println("aaa");
							break;
						case "10":
							System.out.println("Exiting program...");
							isRunning = false;
							break;
						default:
							System.out.println(keyboard + " is not a valid option. Please try again.");
					}

					// Catch any and all program-specific exceptions here to de-clutter your switch-case
					// in case of checked and/or custom exceptions.
				} catch (Exception e) {
					System.out.println("Exception caught in inner try : " + e.getMessage());
				}
			} while (isRunning);
		} catch (Exception e) {
			System.out.println("Exception caught in outer try : " + e.getMessage());
		} finally {
			// Any tasks needed for cleaning up/saving/etc should be performed here.
			// The scanner auto-closes so don't worry about that.
		}
	}
}
