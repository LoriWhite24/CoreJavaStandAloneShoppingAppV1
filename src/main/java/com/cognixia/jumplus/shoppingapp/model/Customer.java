package com.cognixia.jumplus.shoppingapp.model;

/**
 * The model for customer.
 * @author Lori White
 * @version v1 (10/03/2020)
 */
public class Customer {
	private String customerId;
	private String password;
	private String firstName;
	private String lastName;
	private String fullName;
	private String email;
	private String phoneNumber;
	private Integer addressid;
	/**
	 * Default constructor.
	 */
	public Customer() {
		this("-00000000", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", -1);
	}
	/**
	 * Overloaded constructor.
	 * @param customerId the customer's id
	 * @param password the customer's password
	 * @param firstName the customer's first name
	 * @param lastName the customer's last name
	 * @param fullName the customer's full name
	 * @param email the customer's email address
	 * @param phoneNumber the customer's main phone number
	 * @param addressid the customer's current address
	 */
	public Customer(String customerId, String password, String firstName, String lastName, String fullName, String email, String phoneNumber,
			Integer addressid) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.addressid = addressid;
	}
	/**
	 * Retrieves the customer's id.
	 * @return String - the customer's id
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * Updates the customer's id.
	 * @param customerId the customer's id
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * Retrieves the customer's password.
	 * @return String - the customer's password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Updates the customer's password.
	 * @param password the customer's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Retrieves the customer's first name.
	 * @return String - the customer's first name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Updates the customer's first name.
	 * @param firstName the customer's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Retrieves the customer's last name.
	 * @return String - the customer's last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Updates the customer's last name.
	 * @param lastName the customer's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Retrieves the customer's full name.
	 * @return String - the customer's full name
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * Updates the customer's full name.
	 * @param fullName the customer's full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * Retrieves the customer's email address.
	 * @return String - the customer's email address
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Updates the customer's email address.
	 * @param email the customer's email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Retrieves the customer's main phone number.
	 * @return String - the customer's main phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Updates the customer's main phone number.
	 * @param phoneNumber the customer's main phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Retrieves the customer's current address.
	 * @return Integer - the customer's current address
	 */
	public Integer getAddressid() {
		return addressid;
	}
	/**
	 * Updates the customer's current address.
	 * @param addressid the customer's current address
	 */
	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}
	/**
	 * Generates a string representation for this model.
	 * @return String - the string representation for this model
	 */
	@Override
	public String toString() {
		return "Customer [userId=" + customerId + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", fullName=" + fullName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", addressid=" + addressid + "]";
	}
}