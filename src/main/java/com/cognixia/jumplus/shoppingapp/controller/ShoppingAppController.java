package com.cognixia.jumplus.shoppingapp.controller;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cognixia.jumplus.shoppingapp.Main;
import com.cognixia.jumplus.shoppingapp.dao.AddressDAO;
import com.cognixia.jumplus.shoppingapp.dao.CustomerDAO;
import com.cognixia.jumplus.shoppingapp.dao.InvoiceDAO;
import com.cognixia.jumplus.shoppingapp.dao.InvoiceProductDAO;
import com.cognixia.jumplus.shoppingapp.dao.ProductDAO;
import com.cognixia.jumplus.shoppingapp.daoimpl.AddressDAOImpl;
import com.cognixia.jumplus.shoppingapp.daoimpl.CustomerDAOImpl;
import com.cognixia.jumplus.shoppingapp.daoimpl.InvoiceDAOImpl;
import com.cognixia.jumplus.shoppingapp.daoimpl.InvoiceProductDAOImpl;
import com.cognixia.jumplus.shoppingapp.daoimpl.ProductDAOImpl;
import com.cognixia.jumplus.shoppingapp.model.Address;
import com.cognixia.jumplus.shoppingapp.model.Customer;
import com.cognixia.jumplus.shoppingapp.model.Invoice;
import com.cognixia.jumplus.shoppingapp.model.InvoiceProduct;
import com.cognixia.jumplus.shoppingapp.model.Product;
import com.cognixia.jumplus.shoppingapp.utility.ColorsUtility;
import com.cognixia.jumplus.shoppingapp.utility.ConsolePrinterUtility;

/**
 * The controller for the shopping app.
 * @author Lori White
 * @version v1 (10/06/2020)
 */
public class ShoppingAppController {
	private static AddressDAO addressRepo = new AddressDAOImpl();
	private static CustomerDAO customerRepo = new CustomerDAOImpl();
	private static InvoiceDAO invoiceRepo = new InvoiceDAOImpl();
	private static InvoiceProductDAO invoiceProductRepo = new InvoiceProductDAOImpl();
	private static ProductDAO productRepo = new ProductDAOImpl();
	
	private static Customer currentCustomer = null;
	private static Invoice currentInvoice = null;
	private static List<Invoice> currentCustomerInvoices = null;
	private static List<InvoiceProduct> currentInvoiceProducts = null;
	private static Scanner in = Main.getScanner();
	private static final int minChoice = 1;
	private static DecimalFormat df = new DecimalFormat("#,###,##0.00");
	
	@SuppressWarnings("serial")
	private static List<String> products = new ArrayList<String>(), updateMenu = new ArrayList<String>() {{add("Password"); add("First Name"); add("Last Name"); add("Email"); add("Phone Number"); add("Address"); add("Back");}};
	
	/**
	 * Prompts a user to create a new user account.
	 * @return boolean - whether the user is logged in
	 */
	public boolean createNewAccount() {
		String username = "", password = "", fname = "", lname = "", email = "", phone = "", street = "", city = "", state = "", zip = "";
		Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$"), emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"), phonePattern = Pattern.compile("^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$"), streetPattern = Pattern.compile("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"), zipPattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$"), statePattern = Pattern.compile("^[A-Z]{2}$");
        Matcher matcher;
        Address address;
        
        do {
        	ColorsUtility.colorDefault("User Id: (must be at least 8 characters)");
        	username = in.nextLine().trim();
        	if(username.length() < 8 || customerRepo.existsById(username)) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(username.length() < 8 || customerRepo.existsById(username));
        do {
        	ColorsUtility.colorDefault("Password: (must be at least 8 characters with a digit, a lowercase letter, an Uppercase letter, and a special character)");
        	password = in.nextLine().trim();
        	matcher = passPattern.matcher(password);
        	if(!matcher.matches()) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(!matcher.matches());
        do {
        	ColorsUtility.colorDefault("First Name:");
        	fname = in.nextLine().trim();
        	if(fname.equals("")) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(fname.equals(""));
        do {
        	ColorsUtility.colorDefault("Last Name:");
        	lname = in.nextLine().trim();
        	if(lname.equals("")) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(lname.equals(""));
        do {
        	ColorsUtility.colorDefault("Email:");
        	email = in.nextLine().trim();
        	matcher = emailPattern.matcher(email);
        	if(!matcher.matches() || customerRepo.existsByEmail(email)) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(!matcher.matches() || customerRepo.existsByEmail(email));
        do {
        	ColorsUtility.colorDefault("Phone Number:");
        	phone = in.nextLine().trim();
        	matcher = phonePattern.matcher(phone);
        	if(!matcher.matches()) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(!matcher.matches());
        do {
        	ColorsUtility.colorDefault("Street Address:");
        	street = in.nextLine().trim();
        	matcher = streetPattern.matcher(street);
        	if(!matcher.matches()) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(!matcher.matches());
        do {
        	ColorsUtility.colorDefault("City:");
        	city = in.nextLine().trim();
        	if(city.equals("")) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(city.equals(""));
        do {
        	ColorsUtility.colorDefault("State:");
        	state = in.nextLine().trim();
        	matcher = statePattern.matcher(state);
        	if(!matcher.matches()) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(!matcher.matches());
        do {
        	ColorsUtility.colorDefault("Zipcode:");
        	zip = in.nextLine().trim();
        	matcher = zipPattern.matcher(zip);
        	if(!matcher.matches()) {
        		ConsolePrinterUtility.error("input");
        	} 			
        } while(!matcher.matches());
        if(addressRepo.existsByStreetAndZipcode(street, zip)) {
        	address = addressRepo.getByStreetAndZipcode(street, zip);
        } else {
        	address = addressRepo.add(new Address(0, street, city, state, zip));
        }

        currentCustomer = customerRepo.add(new Customer(username, password, fname, lname, "null", email, phone, address.getAddressId()));		
		
        return true;
	}
	/**
	 * Prompts a user to login.
	 * @return boolean - whether the user is logged in
	 */
	public boolean login() {
		String username = "", password = "";
		int counter = 0, max = 7; 
			do {
				ColorsUtility.colorDefault("User Id:");
				username = in.nextLine().trim();
				if(!customerRepo.existsById(username)) {
					ConsolePrinterUtility.error("login");
					counter++;
				}
				if(counter == max) {
					break;
				}
			} while(!customerRepo.existsById(username));

			Customer temp = customerRepo.getById(username);
			do {
				if(counter == max) {
					break;
				}
				ColorsUtility.colorDefault("Password:");
				password = in.nextLine().trim();
				if(!temp.getPassword().equals(password)) {
					ConsolePrinterUtility.error("login");
					counter++;
				} else {
					currentCustomer = customerRepo.getById(username);
					return true;
				}				
			} while(!temp.getPassword().equals(password));

		return false;
	}
	/**
	 * Allows the customer to make a purchase.
	 * @param loggedIn whether the user is logged in
	 */
	public void buyItem(boolean loggedIn) {
		ConsolePrinterUtility.header("purchase");
		if(loggedIn) {
			
		} else {
			
		}
	}
	/**
	 * Allows the customer to make a return.
	 * @param loggedIn whether the user is logged in
	 */
	public void returnItem(boolean loggedIn) {
		ConsolePrinterUtility.header("return");
		if(loggedIn) {
			
		} else {
			
		}
	}
	/**
	 * Displays the customer info.
	 */
	public void displayCustomer() {
		Address address = addressRepo.getById(currentCustomer.getAddressid());
		ColorsUtility.colorOutput("Login Information:\nUser Id: " + currentCustomer.getCustomerId() + " Password: " + currentCustomer.getPassword() + "\nName: " + currentCustomer.getFirstName() + ", " + currentCustomer.getLastName() + "\nContact Information:\nEmail: " + currentCustomer.getEmail() + " Phone Number: " + currentCustomer.getPhoneNumber() + "\nAddress:\n" + address.getStreet() + " " + address.getCity() + ", " + address.getState() + " " + address.getZipcode());
	}
	/**
	 * Lets the current customer update their details.
	 */
	public void updateCustomer() {
		boolean doContinue = true;
		int response = 0;
		String password = "", fname = "", lname = "", email = "", phone = "", street = "", city = "", state = "", zip = "";
		Pattern passPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$"), emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"), phonePattern = Pattern.compile("^(\\+0?1\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$"), streetPattern = Pattern.compile("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)"), zipPattern = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$"), statePattern = Pattern.compile("^[A-Z]{2}$");
        Matcher matcher;
        Address address;
		do {
			ConsolePrinterUtility.header("customer_update");
			do {
				ConsolePrinterUtility.menu(updateMenu);
				ConsolePrinterUtility.menuChoice(updateMenu.size());
				response = Integer.parseInt(in.nextLine().trim());
				if(response < minChoice || response > updateMenu.size()) {
					ConsolePrinterUtility.error("input");
				}
			} while(response < minChoice || response > updateMenu.size());
			ConsolePrinterUtility.clrscr();
			switch(response) {
			case 1:
				do {
	        		ColorsUtility.colorDefault("Password: (must be at least 8 characters with a digit, a lowercase letter, an Uppercase letter, and a special character)");
	        		password = in.nextLine().trim();
	        		matcher = passPattern.matcher(password);
	        		if(!matcher.matches()) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches());
				currentCustomer.setPassword(password);
				customerRepo.update(currentCustomer, "password");
				break;
			case 2:
				do {
	        		ColorsUtility.colorDefault("First Name:");
	        		fname = in.nextLine().trim();
	        		if(fname.equals("")) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(fname.equals(""));
				currentCustomer.setFirstName(fname);
				customerRepo.update(currentCustomer, "first_name");
				break;
			case 3:
				do {
	        		ColorsUtility.colorDefault("Last Name:");
	        		lname = in.nextLine().trim();
	        		if(lname.equals("")) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(lname.equals(""));
				currentCustomer.setLastName(lname);
				customerRepo.update(currentCustomer, "last_name");
				break;
			case 4:
				do {
	        		ColorsUtility.colorDefault("Email:");
	        		email = in.nextLine().trim();
	        		matcher = emailPattern.matcher(email);
	        		if(!matcher.matches() || customerRepo.existsByEmail(email)) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches() || customerRepo.existsByEmail(email));
				currentCustomer.setEmail(email);
				customerRepo.update(currentCustomer, "email");
				break;
			case 5:
				do {
	        		ColorsUtility.colorDefault("Phone Number:");
	        		phone = in.nextLine().trim();
	        		matcher = phonePattern.matcher(phone);
	        		if(!matcher.matches()) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches());
				currentCustomer.setPhoneNumber(phone);
				customerRepo.update(currentCustomer, "phone_number");
				break;
			case 6:
				do {
	        		ColorsUtility.colorDefault("Street Address:");
	        		street = in.nextLine().trim();
	        		matcher = streetPattern.matcher(street);
	        		if(!matcher.matches()) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches());
	        	do {
	        		ColorsUtility.colorDefault("City:");
	        		city = in.nextLine().trim();
	        		if(city.equals("")) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(city.equals(""));
	        	do {
	        		ColorsUtility.colorDefault("State:");
	        		state = in.nextLine().trim();
	        		matcher = statePattern.matcher(state);
	        		if(!matcher.matches()) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches());
	        	do {
	        		ColorsUtility.colorDefault("Zipcode:");
	        		zip = in.nextLine().trim();
	        		matcher = zipPattern.matcher(zip);
	        		if(!matcher.matches()) {
	        			ConsolePrinterUtility.error("input");
	        		} 			
	        	} while(!matcher.matches());
	        	if(addressRepo.existsByStreetAndZipcode(street, zip)) {
	        		address = addressRepo.getByStreetAndZipcode(street, zip);
	        	} else {
	        		address = addressRepo.add(new Address(0, street, city, state, zip));
	        	}
	        	currentCustomer.setAddressid(address.getAddressId());
	        	customerRepo.update(currentCustomer, "address_id");
				break;
			case 7:
				doContinue = false;
				break;
			}
		} while(doContinue);
	}
	/**
	 * Resets the currently saved customer.
	 * @return boolean - the user is not logged in
	 */
	public boolean resetCurrentCustomer() {
		currentCustomer = null;
		return false;
	}
	/**
	 * Retrieves a list of invoice ids that belong to the currently saved customer.
	 * @return List - the invoice ids that belong to the currently saved customer
	 */
	public List<String> getInvoiceMenu() {
		List<String> invoices = new ArrayList<String>();
		currentCustomerInvoices = invoiceRepo.getByCustomerId(currentCustomer.getCustomerId());
		for(Invoice i : currentCustomerInvoices) {
			invoices.add(i.getInvoiceId().toString());
		}
		invoices.add("Back");
		return invoices;		
	}
	/**
	 * Resets the currently saved invoices that belong to the currently saved customer.
	 */
	public void resetCurrentCustomerInvoices() {
		currentCustomerInvoices = null;
	}
	/**
	 * Displays the invoice info.
	 * @param invoiceId the invoice's id
	 */
	public void displayInvoice(Integer invoiceId) {
		Product temp = null;
		currentInvoice = invoiceRepo.getById(invoiceId);
		currentInvoiceProducts = invoiceProductRepo.getByInvoiceId(invoiceId);
		for(InvoiceProduct ip : currentInvoiceProducts) {
			temp = productRepo.getById(ip.getProductId());
			products.add("\t" + temp.getProductId() + "\t\t" + temp.getName() + "\t\t" + temp.getDescription() + "\t$" + df.format(temp.getPrice()) + "\tx" + ip.getQuantity());
		}
		
		ColorsUtility.colorOutput("Customer Name: " + currentCustomer.getFullName() + "\tDate: " + currentInvoice.getOrderDate() + "\nInvoice number: " + currentInvoice.getInvoiceId());
		ColorsUtility.colorChoice("\tProduct ID\tName\t\tDescription\t\tPrice\tQuantity");
		for(String p : products) {
			ColorsUtility.colorMenu(p);
		}
		ColorsUtility.colorOutput("Subtotal: $" + df.format(currentInvoice.getSubTotal()) + "\nTax: $" + df.format(currentInvoice.getTax()) + "\nTotal: $" + df.format(currentInvoice.getTotal()));
		
		products.removeAll(products);
	}	
	/**
	 * Resets the currently saved invoice.
	 */
	public void resetCurrentInvoice() {
		currentInvoice = null;
	}
}
