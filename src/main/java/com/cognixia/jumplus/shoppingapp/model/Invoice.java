package com.cognixia.jumplus.shoppingapp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The model for invoice.
 * @author Lori White
 * @version v1 (10/03/2020)
 */
public class Invoice {
	private Integer invoiceId;
	private String customerId;
	private Timestamp orderDate;
	private Double subTotal;
	private Double salesTax;
	private Double tax;
	private Double total;
	/**
	 * Default constructor.
	 */
	public Invoice() {
		this(-1, "N/A", Timestamp.valueOf(LocalDateTime.now()), 0.0, 0.0, 0.0, 0.0);
	}
	/**
	 * Overloaded constructor.
	 * @param invoiceId the invoice id
	 * @param customerId the customer id of the customer that made this invoice
	 * @param orderDate the date the invoice was ordered
	 * @param subTotal the total cost of the invoice before tax
	 * @param salesTax the sales tax based on the customer's location
	 * @param tax the the tax on the invoice
	 * @param total the total cost of the invoice
	 */
	public Invoice(Integer invoiceId, String customerId, Timestamp orderDate, Double subTotal, Double salesTax,
			Double tax, Double total) {
		super();
		this.invoiceId = invoiceId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.subTotal = subTotal;
		this.salesTax = salesTax;
		this.tax = tax;
		this.total = total;
	}
	/**
	 * Retrieves the invoice id.
	 * @return Integer - the invoice id
	 */
	public Integer getInvoiceId() {
		return invoiceId;
	}
	/**
	 * Updates the invoice id.
	 * @param invoiceId the invoice id
	 */
	public void setInvoiceId(Integer invoiceId) {
		this.invoiceId = invoiceId;
	}
	/**
	 * Retrieves the customer id of the customer that made this invoice.
	 * @return String - the customer id of the customer that made this invoice
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * Updates the customer id of the customer that made this invoice.
	 * @param customerId the customer id of the customer that made this invoice
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * Retrieves the date the invoice was ordered.
	 * @return Timestamp - the date the invoice was ordered
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}
	/**
	 * Updates the date the invoice was ordered.
	 * @param orderDate the date the invoice was ordered
	 */
	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * Retrieves the total cost of the invoice before tax.
	 * @return Double - the total cost of the invoice before tax
	 */
	public Double getSubTotal() {
		return subTotal;
	}
	/**
	 * Updates the total cost of the invoice before tax.
	 * @param subTotal the total cost of the invoice before tax
	 */
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	/**
	 * Retrieves the sales tax based on the customer's location.
	 * @return Double - the sales tax based on the customer's location
	 */
	public Double getSalesTax() {
		return salesTax;
	}
	/**
	 * Updates the sales tax based on the customer's location.
	 * @param salesTax the sales tax based on the customer's location
	 */
	public void setSalesTax(Double salesTax) {
		this.salesTax = salesTax;
	}
	/**
	 * Retrieves the the tax on the invoice.
	 * @return Double - the the tax on the invoice
	 */
	public Double getTax() {
		return tax;
	}
	/**
	 * Updates the the tax on the invoice.
	 * @param tax the the tax on the invoice
	 */
	public void setTax(Double tax) {
		this.tax = tax;
	}
	/**
	 * Retrieves the total cost of the invoice.
	 * @return Double - the total cost of the invoice
	 */
	public Double getTotal() {
		return total;
	}
	/**
	 * Updates the total cost of the invoice.
	 * @param total the total cost of the invoice
	 */
	public void setTotal(Double total) {
		this.total = total;
	}
	/**
	 * Generates a string representation for this model.
	 * @return String - the string representation for this model
	 */
	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", customerId=" + customerId + ", orderDate=" + orderDate
				+ ", subTotal=" + subTotal + ", salesTax=" + salesTax + ", tax=" + tax + ", total=" + total + "]";
	}
}
