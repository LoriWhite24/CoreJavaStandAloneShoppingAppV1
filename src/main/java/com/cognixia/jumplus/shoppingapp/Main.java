package com.cognixia.jumplus.shoppingapp;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.cognixia.jumplus.shoppingapp.model.Customer;
import com.cognixia.jumplus.shoppingapp.model.Invoice;
import com.cognixia.jumplus.shoppingapp.controller.AddressController;
import com.cognixia.jumplus.shoppingapp.controller.CustomerController;
import com.cognixia.jumplus.shoppingapp.controller.InvoiceController;
import com.cognixia.jumplus.shoppingapp.controller.InvoiceProductController;
import com.cognixia.jumplus.shoppingapp.controller.ProductController;
import com.cognixia.jumplus.shoppingapp.dao.AddressDAO;
import com.cognixia.jumplus.shoppingapp.dao.CustomerDAO;
import com.cognixia.jumplus.shoppingapp.dao.InvoiceDAO;
import com.cognixia.jumplus.shoppingapp.dao.InvoiceProductDAO;
import com.cognixia.jumplus.shoppingapp.dao.ProductDAO;

/**
 * Core Java Stand Alone Shopping Application version 1 main functionality.
 * @author Lori White
 * @version v1 (10/03/2020)
 */
public class Main {
	private static AddressDAO addressRepo = new AddressController();
	private static CustomerDAO customerRepo = new CustomerController();
	private static InvoiceDAO invoiceRepo = new InvoiceController();
	private static InvoiceProductDAO invoiceProductRepo = new InvoiceProductController();
	private static ProductDAO productRepo = new ProductController();
	
	private static Scanner in = new Scanner(System.in);
	private static Customer currentCustomer = null;
	private static Invoice currentInvoice = null;
	
	private static DecimalFormat df = new DecimalFormat("#,###,##0.00");
	private static final int minChoice = 1;
	/**
	 * The second entry point for this App.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
	}
}
