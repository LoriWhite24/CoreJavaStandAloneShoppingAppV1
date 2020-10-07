package com.cognixia.jumplus.shoppingapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jumplus.shoppingapp.controller.ShoppingAppController;
import com.cognixia.jumplus.shoppingapp.utility.ColorsUtility;
import com.cognixia.jumplus.shoppingapp.utility.ConsolePrinterUtility;

/**
 * Core Java Stand Alone Shopping Application version 1 main functionality.
 * @author Lori White
 * @version v1 (10/06/2020)
 */
public class Main {	
	private static Scanner in = null;
	private static final int minChoice = 1;
	private static ShoppingAppController controller = new ShoppingAppController();
	@SuppressWarnings("serial")
	private static List<String> topMenu = new ArrayList<String>() {{add("Register"); add("Login"); add("Buy an Item"); add("Return an Item"); add("Exit");}}, loggedInMenu = new ArrayList<String>() {{add("Display Customer Information"); add("Update Customer Information"); add("View all Invoice(s)"); add("Buy an Item"); add("Logout");}}, invoices = new ArrayList<String>();
	/**
	 * The second entry point for this App.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		in = getScanner();
		boolean quit = false, loggedIn = false, wentToInvoices = false, selectedInvoice = false;
		int response;
		String check;

        do {
        	ConsolePrinterUtility.clrscr();
        	ConsolePrinterUtility.header("top");
        	ConsolePrinterUtility.menu(topMenu);
        	do {
        		ConsolePrinterUtility.menuChoice(topMenu.size());
            	response = Integer.parseInt(in.nextLine().trim());
            	if(response > topMenu.size() || response < minChoice) {
            		ConsolePrinterUtility.error("input");
            	}
        	} while(response > topMenu.size() || response < minChoice);
        	ConsolePrinterUtility.clrscr();
        	switch(response) {
        	case 1:
        		ConsolePrinterUtility.header("register");
        		loggedIn = controller.createNewAccount();
        		ConsolePrinterUtility.clrscr();
        		break;
        	case 2:
        		ConsolePrinterUtility.header("login");
        		loggedIn = controller.login();
        		ConsolePrinterUtility.clrscr();
        		break;
        	case 3:
        		controller.buyItem(loggedIn);
        		break;
        	case 4:
        		controller.returnItem(loggedIn);
        		break;
        	case 5:
        		quit = true;
        		break;
        	}
        	if(loggedIn) {
        		do {
        			ConsolePrinterUtility.header("logged_in");
        			ConsolePrinterUtility.menu(loggedInMenu);
        			do {
        				ConsolePrinterUtility.menuChoice(loggedInMenu.size());
        				response = Integer.parseInt(in.nextLine().trim());
        				if(response > loggedInMenu.size() || response < minChoice) {
        					ConsolePrinterUtility.error("input");
        				}
        			} while(response > loggedInMenu.size() || response < minChoice);
        			ConsolePrinterUtility.clrscr();
        			switch(response) {
        			case 1:
        				ConsolePrinterUtility.header("customer");
        				controller.displayCustomer();
        				break;
        			case 2:
        				controller.updateCustomer();
        				break;
        			case 3:
        				wentToInvoices = true;
        				invoices = controller.getInvoiceMenu();
        				break;
        			case 4: 
        				controller.buyItem(loggedIn);
        				break;
        			case 5:
        				loggedIn = controller.resetCurrentCustomer();
        				break;
        			}
        			if(wentToInvoices) {
        				do {
        					ConsolePrinterUtility.header("invoices");
        					do {
        						ConsolePrinterUtility.menu(invoices);
        						ConsolePrinterUtility.menuChoice(invoices.size());
        						response = Integer.parseInt(in.nextLine().trim());
        						if(response < minChoice || response > invoices.size()) {
        							ConsolePrinterUtility.error("input");
        						}
        					} while(response < minChoice || response > invoices.size());
        					ConsolePrinterUtility.clrscr();
        					if(response == invoices.size()) {
        						invoices.removeAll(invoices);
        						controller.resetCurrentCustomerInvoices();
        						wentToInvoices = false;
        					} else {
            					selectedInvoice = true;
            					do {
            						controller.displayInvoice(Integer.parseInt(invoices.get(response - 1)));
                					ConsolePrinterUtility.header("invoice");
                					do {
                						ColorsUtility.colorDefault("Do you want to request a return on this invoice? (Yes or No)");
                						check = in.nextLine().trim().toLowerCase().substring(0, 1);
                						if(!check.equals("y") && !check.equals("n")) {
                							ConsolePrinterUtility.error("input");
                						}
                					} while(!check.equals("y") && !check.equals("n"));
                    				ConsolePrinterUtility.clrscr();
                    				if(check.equals("y")) {
                    					controller.returnItem(loggedIn);
                    				} else {
                    					selectedInvoice = false;
                    					controller.resetCurrentInvoice();
                    				}
            					}while(selectedInvoice);            					
        					}
        				}while(wentToInvoices);
        			}
        		} while(loggedIn);
        	}
        } while(!quit);
        in.close();
	}
	/**
	 * Retrieves the current scanner object.
	 * @return Scanner - the current scanner object
	 */
	public static Scanner getScanner() {

		if (in == null) { 
			in = new Scanner(System.in);
		}

		return in;
	}
}
