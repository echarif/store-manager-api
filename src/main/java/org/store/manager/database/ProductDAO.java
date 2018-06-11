package org.store.manager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.store.manager.model.Product;

public class ProductDAO {

	DBConnection dbCon;
	Connection conn;
	ResultSet rslt;

	public Product getProduct(long id) {
		String query = "select * from store.str_products where cod_product="
				+ id;
		dbCon = new DBConnection();
		Product product = null;
		try {
			conn = DBConnection.setDataBaseConnection();
			rslt = dbCon.getResultSet(query, conn);

			if (rslt.next()) {
				product = new Product();
				product.setId(id);
				product.setDescription(rslt.getString(2));
				product.setStock(rslt.getLong(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return product;
	}

	public ArrayList<Product> getProducts() {
		String query = "select * from store.str_products";
		dbCon = new DBConnection();
		Product product = null;

		ArrayList<Product> products = new ArrayList<Product>();
		try {
			conn = DBConnection.setDataBaseConnection();
			rslt = dbCon.getResultSet(query, conn);

			while (rslt.next()) {

				product = new Product(rslt.getLong(1), rslt.getString(2),
						rslt.getLong(3));
				if (product != null) {
					products.add(product);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("SIZE OF ARRAY: "+products.size());
		return products;
	}

	public Product addProduct(Product product) {
		String query = "INSERT INTO STR_PRODUCTS (TXT_DESCRIPTION,TXT_STOCK) VALUES(?,?)";
		dbCon = new DBConnection();

		try {
			conn = DBConnection.setDataBaseConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, product.getDescription());
			preparedStatement.setLong(2, product.getStock());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return product;
	}

	public void deleteProduct(long id) {
		String query = "DELETE FROM STR_PRODUCTS WHERE COD_PRODUCT=" + id;
		dbCon = new DBConnection();

		try {
			conn = DBConnection.setDataBaseConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.execute();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
