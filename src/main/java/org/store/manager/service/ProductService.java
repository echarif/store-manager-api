package org.store.manager.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.store.manager.database.DBConnection;
import org.store.manager.database.ProductDAO;
import org.store.manager.model.Product;

public class ProductService {

	DBConnection dbCon;
	Connection conn;
	ResultSet rslt;

	/*public List<Product> getAllProducts() {
		return new ArrayList<Product>(products.values());
	}
*/
	public List<Product> getAllProducts() {

		ProductDAO prd = new ProductDAO();
		List<Product> products = prd.getProducts();
		return products;
	}

	/*
	 * public List<Message> getAllMessagesPaginated(int start, int size) {
	 * ArrayList<Message> list = new ArrayList<Message>(products.values());
	 * return list.subList(start, start + size); }
	 */

	public Product getProduct(long id) {

		ProductDAO prd = new ProductDAO();
		Product product = prd.getProduct(id);
		System.out.println("Description: " + product.getDescription());

		Long idPrd = product.getId();
		if (idPrd == null) {
			throw new NullPointerException();
		}
		return product;
	}

	public Product addProduct(Product prd) {

		ProductDAO prDao = new ProductDAO();
		prDao.addProduct(prd);
		return prd;
	}

	/*
	 * public Message updateMessage(Message message) { if (message.getId() <= 0)
	 * { return null; }
	 * 
	 * products.put(message.getId(), message); return message; }
	 */

	public void removeProduct(long id) {
		ProductDAO prd = new ProductDAO();
		prd.deleteProduct(id);
	}

}
