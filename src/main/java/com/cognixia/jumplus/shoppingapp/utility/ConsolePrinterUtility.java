package com.cognixia.jumplus.shoppingapp.utility;

import java.io.IOException;
import java.util.List;

/**
 * The utility for storing reusable output formating for this app.
 * @author Lori White
 * @version v1 (10/05/2020)
 */
public class ConsolePrinterUtility {
	/**
	 * Generates the header, colors, and then prints it to the header.
	 * @param choice the header type
	 */
	public static void header(String choice) {
		String header = "";
		
		switch(choice) {
		case "top":
			header = "+----------------+\n| Welcome Guest! |\n+----------------+";
			break;
		case "logged_in":
			header = "+-------------------+\n| Welcome Customer! |\n+-------------------+";
			break;
		case "register":
			header = "+---------------------------+\n| Enter Details to Register |\n+---------------------------+";
			break;
		case "purchase":
			header = "+------------------------------+\n| Items Available for Purchase |\n+------------------------------+";
			break;
		case "login":
			header = "+---------------------+\n| Enter Login Details |\n+---------------------+";
			break;
		case "customer":
			header = "+------------------+\n| Customer Details |\n+------------------+";
			break;
		case "customer_update":
			header = "+-------------------------+\n| Update Customer Details |\n+-------------------------+";
			break;
		case "invoices":
			header = "+---------------+\n| Your Invoices |\n+---------------+";
			break;
		case "return":
			header = "+----------------+\n| Making a Return |\n+----------------+";
			break;
		}
		ColorsUtility.colorHeader(header);
	}
	/**
	 * Generates the menuChoice, colors, and then prints it to the menuChoice.
	 * @param numChoice the number of choices
	 */
	public static void menuChoice(int numChoice) {
		String menuChoice = "Enter Choice (";
		
		for(int i = 1; i <= numChoice; i++) {
			if(i == numChoice) {
				menuChoice += "or " + i + "):";
			} else {
				menuChoice += i + ", ";
			}
		}
		ColorsUtility.colorChoice(menuChoice);
	}
	/**
	 * Generates the menu, colors, and then prints it to the menu.
	 * @param choices the menu of choices to print
	 */
	public static void menu(List<String> choices) {
		String menu = "";
		for(int i = 1; i <= choices.size(); i++) {
			menu += i + ". " + choices.get(i - 1);
			if(i != choices.size()) {
				menu += "\n";
			}
		}
		ColorsUtility.colorMenu(menu);
	}
	/**
	 * Generates the error, colors, and then prints it to the error.
	 * @param choice the error type
	 */
	public static void error(String choice) {
		String error = "";
		
		switch(choice) {
		case "login":
			error = "Invalid Credentials. Please try again!";
			break;
		case "input":
			error = "Invalid Input. Please try again!";
			break;
		case "return":
			error = "Cannot make a Return.";
			break;
		case "out_of_stock":
			error = "This item is Out of Stock.";
			break;
		}
		ColorsUtility.colorError(error);
	}
	/**
	 * Clears the console in command line.
	 */
    public static void clrscr(){

        //Clears Screen in java

        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {
        	System.out.printf("something went wrong :(");
        }

    }
}
