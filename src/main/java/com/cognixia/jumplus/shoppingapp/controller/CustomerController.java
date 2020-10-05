package com.cognixia.jumplus.shoppingapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jumplus.shoppingapp.config.ConnectionManagerProperties;
import com.cognixia.jumplus.shoppingapp.dao.CustomerDAO;
import com.cognixia.jumplus.shoppingapp.model.Customer;

/**
 * The controller for customer.
 * @author Lori White
 * @version v1 (10/04/2020)
 */
public class CustomerController implements CustomerDAO {
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves a customer by id.
	 * @param id the customer id to search by
	 * @return Customer - the customer found by id
	 */
	@Override
	public Customer getById(String id) {
		Customer customer = null;

		try(PreparedStatement pstmt = conn.prepareStatement("select * from shopping_app.customer where customer_id = ?")) {

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));

			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return customer;
	}
	/**
	 * Finds whether a customer exists by an id. 
	 * @param id the customer id to search by
	 * @return boolean - whether a customer exists by an id
	 */
	@Override
	public boolean existsById(String id) {
		return getById(id) != null;
	}
	/**
	 * Updates a customer.
	 * @param customer the customer to update
	 * @param column the specific column to update or if null update all
	 * @return boolean - whether the customer was updated 
	 */
	@Override
	public boolean update(Customer customer, String column) {
		try(PreparedStatement pstmt = conn.prepareStatement("update shopping_app.customer set " + column + " = ? where customer_id = ?")) {
			
			switch(column) {
				case "password":
					pstmt.setString(1, customer.getPassword());
				break;
				case "first_name":
					pstmt.setString(1, customer.getFirstName());
				break;
				case "last_name":
					pstmt.setString(1, customer.getLastName());
				break;
				case "email":
					pstmt.setString(1, customer.getEmail());
				break;
				case "phone_number":
					pstmt.setString(1, customer.getPhoneNumber());
				break;
				case "address_id":
					pstmt.setInt(1, customer.getAddressid());
				break;
			}
			
			pstmt.setString(2, customer.getCustomerId());

			int updated = pstmt.executeUpdate();

			if(updated > 0) {
				return true;
			}
			pstmt.close();
			
		} catch(SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * Adds a customer.
	 * @param customer the customer to add
	 * @return Customer - the added customer
	 */
	@Override
	public Customer add(Customer customer) {
		if(!existsById(customer.getCustomerId())) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into shopping_app.customer (`customer_id`, `password`, `first_name`, `last_name`, `email`, `phone_number`, `address_id`) values(?,?,?,?,?,?,?)");

				pstmt.setString(1, customer.getCustomerId());
				pstmt.setString(2, customer.getPassword());
				pstmt.setString(3, customer.getFirstName());
				pstmt.setString(4, customer.getLastName());
				pstmt.setString(5, customer.getEmail());
				pstmt.setString(6, customer.getPhoneNumber());
				pstmt.setInt(7,  customer.getAddressid());


				int insert = pstmt.executeUpdate();

				if(insert > 0) {
					return getById(customer.getCustomerId());
				}


				pstmt.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * Retrieves a customer by email.
	 * @param email the customer email to search by
	 * @return Customer - the customer found by email
	 */
	@Override
	public Customer getByEmail(String email) {
		Customer customer = null;

		try(PreparedStatement pstmt = conn.prepareStatement("select * from shopping_app.customer where email = ?")) {

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));

			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return customer;
	}
	/**
	 * Finds whether a customer exists by an email. 
	 * @param email the customer email to search by
	 * @return boolean - whether a customer exists by an email
	 */
	@Override
	public boolean existsByEmail(String email) {
		return getByEmail(email) != null;
	}
}
