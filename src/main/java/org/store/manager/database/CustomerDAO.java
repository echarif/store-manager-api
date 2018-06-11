package org.store.manager.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.store.manager.model.Customer;

public class CustomerDAO {
	
	DBConnection dbCon;
	Connection conn;
	ResultSet rslt;

	public Customer getCustomer(Long id) {
		String query = "select * from store.str_customers where cod_customer="
				+ id;
		dbCon = new DBConnection();
		Customer customer = null;
		try {
			conn = DBConnection.setDataBaseConnection();
			rslt = dbCon.getResultSet(query, conn);

			if (rslt.next()) {
				customer = new Customer();
				customer.setId(id);
				customer.setName(rslt.getString(2));
				customer.setSurname(rslt.getString(3));
				customer.setMobilePhone(rslt.getString(4));
				customer.setPhone(rslt.getString(5));
				customer.setEmail(rslt.getString(6));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		//System.out.println("CUSTOMER ID: "+customer.getId());
		return customer;
	}

	public ArrayList<Customer> getCustomers() {
		String query = "select * from store.str_customers";
		dbCon = new DBConnection();
		Customer customer = null;

		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			conn = DBConnection.setDataBaseConnection();
			rslt = dbCon.getResultSet(query, conn);

			while (rslt.next()) {

				customer = new Customer(rslt.getLong(1), rslt.getString(2),
						rslt.getString(3),rslt.getString(4),rslt.getString(5),rslt.getString(6));
				if (customer != null) {
					customers.add(customer);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("SIZE OF ARRAY: "+customers.size());
		return customers;
	}

	public Customer addCustomer(Customer customer) {
		String query = "insert into str_customers(TXT_NAME,TXT_SURNAME,TXT_MOBILE_PHONE,TXT_PHONE,TXT_EMAIL) values(?,?,?,?,?)";
		dbCon = new DBConnection();

		try {
			conn = DBConnection.setDataBaseConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getSurname());
			preparedStatement.setString(3, customer.getMobilePhone());
			preparedStatement.setString(4, customer.getPhone());
			preparedStatement.setString(5, customer.getEmail());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return customer;
	}

	public void deleteCustomer(long id) {
		String query = "DELETE FROM STR_CUSTOMERS WHERE COD_CUSTOMER=" + id;
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
