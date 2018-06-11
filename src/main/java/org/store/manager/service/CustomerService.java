package org.store.manager.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.store.manager.database.CustomerDAO;
import org.store.manager.database.DBConnection;
import org.store.manager.model.Customer;

@Repository
public class CustomerService {

    DBConnection dbCon;
    Connection conn;
    ResultSet rslt;

    public List<Customer> getAllCustomers() {

        CustomerDAO cust = new CustomerDAO();
        List<Customer> customers = cust.getCustomers();
        return customers;
    }

    public Customer getCustomer(Long id) {

        CustomerDAO cust = new CustomerDAO();
        Customer customer = cust.getCustomer(id);

        Long idPrd = customer.getId();

        System.out.println("ID CUSTOMER: " + idPrd);
        if (idPrd == null) {
            throw new NullPointerException("CUSTOMER ID NOT FOUND");
        }
        return customer;
    }

    public void addCustomer(Customer cust) {

        CustomerDAO custDao = new CustomerDAO();
        custDao.addCustomer(cust);
    }

    public void removeCustomer(long id) {
        CustomerDAO cust = new CustomerDAO();
        cust.deleteCustomer(id);
    }

    public boolean isValid(Customer customer) {
        if (!customer.getEmail().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
