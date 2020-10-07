package com.cognixia.jumplus.shoppingapp.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jumplus.shoppingapp.config.ConnectionManagerProperties;
import com.cognixia.jumplus.shoppingapp.dao.InvoiceProductDAO;
import com.cognixia.jumplus.shoppingapp.model.InvoiceProduct;

/**
 * The controller for invoice-product.
 * @author Lori White
 * @version v1 (10/04/2020)
 */
public class InvoiceProductDAOImpl implements InvoiceProductDAO {
	private Connection conn = ConnectionManagerProperties.getConnection();
	/**
	 * Retrieves an invoice-product by id.
	 * @param id the invoice-product id to search for
	 * @return InvoiceProduct - the invoice-product found by id
	 */
	@Override
	public InvoiceProduct getById(Integer id) {
		InvoiceProduct invoiceProduct = null;

		try(PreparedStatement pstmt = conn.prepareStatement("select * from shopping_app.invoice_product where invoice_product_id = ?")) {

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				invoiceProduct = new InvoiceProduct(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getBoolean(6), rs.getInt(7));
			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return invoiceProduct;
	}
	/**
	 * Retrieves a list of invoice-product objects by an invoice id.
	 * @param invoiceId the invoice id to search by
	 * @return List - the list of invoice-product objects by an invoice id
	 */
	@Override
	public List<InvoiceProduct> getByInvoiceId(Integer invoiceId) {
		List<InvoiceProduct> invoiceProduct = new ArrayList<InvoiceProduct>();

		try(PreparedStatement pstmt = conn.prepareStatement("select * from shopping_app.invoice_product where invoice_id = ?")) {

			pstmt.setInt(1, invoiceId);

			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				invoiceProduct.add(new InvoiceProduct(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getBoolean(5), rs.getBoolean(6), rs.getInt(7)));
			}

			pstmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return invoiceProduct;
	}
	/**
	 * Adds an invoice linked to a product.
	 * @param invoiceProduct the invoice linked to a product
	 * @return InvoiceProduct - the added invoice linked to a product
	 */
	@Override
	public InvoiceProduct add(InvoiceProduct invoiceProduct) {
		if(getById(invoiceProduct.getInvoiceProductId()) == null) {
			try {
				PreparedStatement pstmt = conn.prepareStatement("insert into shopping_app.invoice_product (`invoice_product_id`, `invoice_id`, `product_id`, `quantity`) values(?,?,?,?)");

				pstmt.setInt(1, invoiceProduct.getInvoiceProductId());
				pstmt.setInt(2, invoiceProduct.getInvoiceId());
				pstmt.setString(3, invoiceProduct.getProductId());
				pstmt.setInt(4, invoiceProduct.getQuantity());

				int insert = pstmt.executeUpdate();

				if(insert > 0) {
					return getById(invoiceProduct.getInvoiceProductId());
				}


				pstmt.close();

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		
		return null;
	}
	/**
	 * Updates an invoice-product.
	 * @param invoiceProduct the invoice-product to update
	 * @param column the specific column to update or if null update all
	 * @return boolean - whether the invoice-product was updated
	 */
	@Override
	public boolean update(InvoiceProduct invoiceProduct, String column) {
		try(PreparedStatement pstmt = conn.prepareStatement("update shopping_app.invoice_product set " + column + " = ? where invoice_product_id = ?")) {
			
			switch(column) {
				case "can_return":
					pstmt.setBoolean(1, invoiceProduct.getCanReturn());
				break;
				case "requested_return":
					pstmt.setBoolean(1, invoiceProduct.getRequestedReturn());
				break;
				case "amount_requested_return":
					pstmt.setInt(1, invoiceProduct.getAmountRequestedReturn());
				break;
			}
			
			pstmt.setInt(2, invoiceProduct.getInvoiceProductId());

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
	 * Updates an invoice-product based on giving the ok on a requested return.
	 * @param invoiceProduct the invoice-product to update
	 * @return boolean - whether the invoice-product was updated
	 */
	@Override
	public boolean requestedReturnValidated(InvoiceProduct invoiceProduct) {
		boolean updated = false;
		try(CallableStatement cstmt = conn.prepareCall("{call ok_return(?)}")) {
		   cstmt.setInt(1, invoiceProduct.getInvoiceProductId());
		   updated = cstmt.execute();
		   cstmt.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}
}
