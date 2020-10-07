package com.cognixia.jumplus.shoppingapp.utility;

import com.cognixia.jumplus.shoppingapp.dao.CustomerDAO;
import com.cognixia.jumplus.shoppingapp.daoimpl.CustomerDAOImpl;

/**
 * The utility for generating guests for this app.
 * @author Lori White
 * @version v1 (10/05/2020)
 */
public class GuestGeneratorUtility {
	/**
	 * Generates an unique customer id. 
	 * @return String - the unique customer id
	 */
	public static String generateNewAccountNumber() {
		CustomerDAO repo = new CustomerDAOImpl();
			
		Integer count = repo.getNumberOfGuests();
		count++;
		
		return "guest" + count.toString();
	}
	/**
	 * Tests the integration of this class's generateNewAccountNumber function with the dollars_bank database.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		System.out.println(generateNewAccountNumber());
	}
}
