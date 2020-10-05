package com.cognixia.jumplus.shoppingapp.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognixia.jumplus.shoppingapp.config.ConnectionManagerProperties;
import com.cognixia.jumplus.shoppingapp.dao.ProductDAO;
import com.cognixia.jumplus.shoppingapp.model.Product;

/**
 * The controller for product.
 * @author Lori White
 * @version v1 (10/04/2020)
 */
public class ProductController implements ProductDAO {
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves a product by id.
	 * @param id the product id to search by
	 * @return Product - the product found by id
	 */
	@Override
	public Product getById(String id) {
		Product product = null;

		try(PreparedStatement pstmt = conn.prepareStatement("select * from shopping_app.product where product_id = ?")) {

			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getBoolean(6));
			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return product;
	}
	/**
	 * Retrieves a product by name.
	 * @param name the product name to search by
	 * @return Product - the product found by name
	 */
	@Override
	public Product getByName(String name) {
		Product product = null;

		try(PreparedStatement pstmt = conn.prepareStatement("select * from shopping_app.product where name = ?")) {

			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getBoolean(6));
			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return product;
	}
	/**
	 * Finds whether a product exists by a name. 
	 * @param name the product name to search by
	 * @return boolean - whether a product exists by a name
	 */
	@Override
	public boolean existsByName(String name) {
		return getByName(name) != null;
	}
}
