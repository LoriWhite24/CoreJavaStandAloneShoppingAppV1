package com.cognixia.jumplus.shoppingapp.dao;

import com.cognixia.jumplus.shoppingapp.model.Product;

/**
 * The DAO for product.
 * @author Lori White
 * @version v1 (10/03/2020)
 */
public interface ProductDAO {
	/**
	 * Retrieves a product by id.
	 * @param id the product id to search by
	 * @return Product - the product found by id
	 */
	public Product getById(String id);
	/**
	 * Retrieves a product by name.
	 * @param name the product name to search by
	 * @return Product - the product found by name
	 */
	public Product getByName(String name);
	/**
	 * Finds whether a product exists by a name. 
	 * @param name the product name to search by
	 * @return boolean - whether a product exists by a name
	 */
	public boolean existsByName(String name);
}
