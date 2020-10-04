package com.cognixia.jumplus.shoppingapp.dao;

import com.cognixia.jumplus.shoppingapp.model.Address;

/**
 * The DAO for address.
 * @author Lori White
 * @version v1 (10/03/2020)
 */
public interface AddressDAO {
	/**
	 * Retrieves an address by id.
	 * @param id the id to search by
	 * @return Address - the address found by id
	 */
	public Address getById(int id);
	/**
	 * Retrieves an address by a street and a zipcode.
	 * @param street the street to search by
	 * @param zipcode the zipcode to search by
	 * @return Address - the address found by a street and a zipcode
	 */
	public Address getByStreetAndZipcode(String street, String zipcode);
	/**
	 * Finds whether an address exists based on the street and zipcode.
	 * @param street the street to search for
	 * @param zipcode the zipcode to search for
	 * @return boolean - whether an address exists based on the street and zipcode
	 */
	public boolean existsByStreetAndZipcode(String street, String zipcode);
	/**
	 * Adds an address.
	 * @param address the address to add
	 * @return Address - the added address
	 */
	public Address add(Address address);
}