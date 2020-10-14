package com.cognixia.jumplus.shoppingapp.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jumplus.shoppingapp.config.ConnectionManagerProperties;
import com.cognixia.jumplus.shoppingapp.dao.InvoiceDAO;
import com.cognixia.jumplus.shoppingapp.model.Invoice;

/**
 * The controller for invoice.
 * @author Lori White
 * @version v1 (10/04/2020)
 */
public class InvoiceDAOImpl implements InvoiceDAO {
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves an invoice by id.
	 * @param id the invoice id to search by
	 * @return Invoice -  the invoice found by id
	 */
	@Override
	public Invoice getById(Integer id) {
		Invoice invoice = null;

		try(PreparedStatement pstmt = conn.prepareStatement("select * from shopping_app.invoice where invoice_id = ?")) {

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				invoice = new Invoice(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7));
			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return invoice;
	}
	/**
	 * Finds whether an invoice exists by id. 
	 * @param id the invoice id to search by
	 * @return boolean - whether an invoice exists by id
	 */
	@Override
	public boolean existsById(Integer id) {
		return (getById(id) != null);
	}
	/**
	 * Retrieves a list of invoices based on a customer's id.
	 * @param customerId the customer id to search by
	 * @return List - the list of invoices based on the customer's id
	 */
	@Override
	public List<Invoice> getByCustomerId(String customerId) {
		List<Invoice> invoice = new ArrayList<Invoice>();

		try(PreparedStatement pstmt = conn.prepareStatement("select * from shopping_app.invoice where customer_id = ?")) {

			pstmt.setString(1, customerId);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				invoice.add(new Invoice(rs.getInt(1), rs.getString(2), rs.getTimestamp(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7)));
			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return invoice;
	}
	/**
	 * Adds an invoice.
	 * @param invoice the invoice to add
	 * @return Invoice - the added invoice
	 */
	@Override
	public Invoice add(Invoice invoice) {
		if(getById(invoice.getInvoiceId()) == null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into shopping_app.invoice (`invoice_id`, `customer_id`, `order_date`) values(?,?,?)");
				CallableStatement cstmt = conn.prepareCall("{call set_tax_rate(?)}");
						
				pstmt.setInt(1, invoice.getInvoiceId());
				pstmt.setString(2, invoice.getCustomerId());
				pstmt.setTimestamp(3, invoice.getOrderDate());
				cstmt.setString(1, invoice.getCustomerId());

				int insert = pstmt.executeUpdate();
				boolean updated = cstmt.execute();

				if(insert > 0 && updated) {
					return getById(invoice.getInvoiceId());
				}
				
				cstmt.close();
				pstmt.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * Updates the sub total of the invoice.
	 * @param invoice the invoice to update
	 * @return boolean - whether the invoice was updated
	 */
	@Override
	public boolean updateSubTotal(Invoice invoice) {
		boolean updated = false;
		try(CallableStatement cstmt = conn.prepareCall("{call set_sub_total(?)}")) {
		   cstmt.setInt(1, invoice.getInvoiceId());
		   updated = cstmt.execute();
		   cstmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
}
