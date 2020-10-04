package com.cognixia.jumplus.shoppingapp.model;

/**
 * The model for product.
 * @author Lori White
 * @version v1 (10/03/2020)
 */
public class Product {
	private String productId;
	private String name;
	private String description;
	private Double price;
	private Integer stock;
	private Boolean inStock;
	/**
	 * Default constructor.
	 */
	public Product() {
		this("N/A", "N/A", "N/A", 0.0, 0, false);
	}
	/**
	 * Overloaded constructor.
	 * @param productId the product id
	 * @param name the name of the product 
	 * @param description the description of the product
	 * @param price the cost of the product
	 * @param stock the current stock of the product
	 * @param inStock whether the product is in stock or not
	 */
	public Product(String productId, String name, String description, Double price, Integer stock, Boolean inStock) {
		super();
		this.productId = productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.inStock = inStock;
	}
	/**
	 * Retrieves the product id.
	 * @return String - the product id
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * Updates the product id.
	 * @param productId the product id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * Retrieves the name of the product.
	 * @return String - the name of the product
	 */
	public String getName() {
		return name;
	}
	/**
	 * Updates the name of the product.
	 * @param name the name of the product
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Retrieves the description of the product.
	 * @return String - the description of the product
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Updates the description of the product.
	 * @param description the description of the product
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Retrieves the cost of the product.
	 * @return Double - the cost of the product
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * Updates the cost of the product.
	 * @param price the cost of the product
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * Retrieves the current stock of the product.
	 * @return Integer - the current stock of the product
	 */
	public Integer getStock() {
		return stock;
	}
	/**
	 * Updates the current stock of the product.
	 * @param stock the current stock of the product
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**
	 * Retrieves whether the product is in stock or not.
	 * @return Boolean - whether the product is in stock or not
	 */
	public Boolean getInStock() {
		return inStock;
	}
	/**
	 * Updates whether the product is in stock or not.
	 * @param inStock whether the product is in stock or not
	 */
	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}
	/**
	 * Generates a string representation for this model.
	 * @return String - the string representation for this model
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", price="
				+ price + ", stock=" + stock + ", inStock=" + inStock + "]";
	}
}
