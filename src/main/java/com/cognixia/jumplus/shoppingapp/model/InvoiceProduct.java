package com.cognixia.jumplus.shoppingapp.model;

/**
 * The model for invoice_product.
 * @author Lori White
 * @version v1 (10/03/2020)
 */
public class InvoiceProduct {
	private Integer invoiceProductId;
	private Integer invoiceId;
	private String productId;
	private Integer quantity;
	private Boolean canReturn;
	private Boolean requestedReturn;
	private Integer amountRequestedReturn;
	/**
	 * Default constructor.
	 */
	public InvoiceProduct() {
		this(0, 0, "N/A", 0, false, false, 0);
	}
	/**
	 * Overloaded constructor.
	 * @param invoiceProductId the invoice product id
	 * @param invoiceId the invoice id connected to the product involved
	 * @param productId the product id connected to the invoice involved
	 * @param quantity the quantity of the product involved 
	 * @param canReturn whether the product involved can be returned
	 * @param requestedReturn whether the customer has requested a return
	 * @param amountRequestedReturn the amount to return when a return is requested 
	 */
	public InvoiceProduct(Integer invoiceProductId, Integer invoiceId, String productId, Integer quantity,
			Boolean canReturn, Boolean requestedReturn, Integer amountRequestedReturn) {
		super();
		this.invoiceProductId = invoiceProductId;
		this.invoiceId = invoiceId;
		this.productId = productId;
		this.quantity = quantity;
		this.canReturn = canReturn;
		this.requestedReturn = requestedReturn;
		this.amountRequestedReturn = amountRequestedReturn;
	}
	/**
	 * Retrieves the invoice product id.
	 * @return Integer - the invoice product id
	 */
	public Integer getInvoiceProductId() {
		return invoiceProductId;
	}
	/**
	 * Updates the invoice product id.
	 * @param invoiceProductId the invoice product id
	 */
	public void setInvoiceProductId(Integer invoiceProductId) {
		this.invoiceProductId = invoiceProductId;
	}
	/**
	 * Retrieves the invoice id connected to the product involved.
	 * @return Integer - the invoice id connected to the product involved
	 */
	public Integer getInvoiceId() {
		return invoiceId;
	}
	/**
	 * Updates the invoice id connected to the product involved.
	 * @param invoiceId the invoice id connected to the product involved
	 */
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}
	/**
	 * Retrieves the product id connected to the invoice involved.
	 * @return String - the product id connected to the invoice involved
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * Updates the product id connected to the invoice involved.
	 * @param productId the product id connected to the invoice involved
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * Retrieves the quantity of the product involved.
	 * @return Integer - the quantity of the product involved
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * Updates the quantity of the product involved.
	 * @param quantity the quantity of the product involved
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * Retrieves whether the product involved can be returned.
	 * @return Boolean - whether the product involved can be returned
	 */
	public Boolean getCanReturn() {
		return canReturn;
	}
	/**
	 * Updates whether the product involved can be returned.
	 * @param canReturn whether the product involved can be returned
	 */
	public void setCanReturn(Boolean canReturn) {
		this.canReturn = canReturn;
	}
	/**
	 * Retrieves whether the customer has requested a return.
	 * @return Boolean - whether the customer has requested a return
	 */
	public Boolean getRequestedReturn() {
		return requestedReturn;
	}
	/**
	 * Updates whether the customer has requested a return.
	 * @param requestedReturn whether the customer has requested a return
	 */
	public void setRequestedReturn(Boolean requestedReturn) {
		this.requestedReturn = requestedReturn;
	}
	/**
	 * Retrieves the amount to return when a return is requested.
	 * @return Integer - the amount to return when a return is requested
	 */
	public Integer getAmountRequestedReturn() {
		return amountRequestedReturn;
	}
	/**
	 * Updates the amount to return when a return is requested.
	 * @param amountRequestedReturn the amount to return when a return is requested
	 */
	public void setAmountRequestedReturn(Integer amountRequestedReturn) {
		this.amountRequestedReturn = amountRequestedReturn;
	}
	/**
	 * Generates a string representation for this model.
	 * @return String - the string representation for this model
	 */
	@Override
	public String toString() {
		return "InvoiceProduct [invoiceProductId=" + invoiceProductId + ", invoiceId=" + invoiceId + ", productId="
				+ productId + ", quantity=" + quantity + ", canReturn=" + canReturn + ", requestedReturn="
				+ requestedReturn + ", amountRequestedReturn=" + amountRequestedReturn + "]";
	}
}
