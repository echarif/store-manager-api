package org.store.manager.database;

import org.store.manager.model.Customer;
import org.store.manager.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by hasan on 13/01/2018.
 */
public class EmployeeDAO {

    DBConnection dbCon;
    Connection conn;
    ResultSet rslt;

    public Employee getEmployee(long id) {
        String query = "select * from store.str_employee where cod_employees="
                + id;
        dbCon = new DBConnection();
        Employee employee = null;
        try {
            conn = DBConnection.setDataBaseConnection();
            rslt = dbCon.getResultSet(query, conn);

            if (rslt.next()) {
                employee = new Employee();
                employee.setCod_employee(id);
                employee.setName(rslt.getString(2));
                employee.setSurname(rslt.getString(3));
                employee.setMobileNumber(rslt.getString(4));
                employee.setPhoneNumber(rslt.getString(5));
                employee.setEmail(rslt.getString(6));
                employee.setEmployeeType(rslt.getString(7));
                employee.setPassword(rslt.getString(8));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("CUSTOMER ID: "+employee.getCod_employee());
        return employee;
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

    public Employee addCustomer(Employee employee) {
        String query = "insert into str_employees(TXT_NAME,TXT_SURNAME,TXT_MOBILE_PHONE,TXT_PHONE,TXT_EMAIL,EMPLOYEE_TYPE,TXT_PASSWORD) values(?,?,?,?,?,?,?)";
        dbCon = new DBConnection();
        try {
            conn = DBConnection.setDataBaseConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getMobileNumber());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setString(6, employee.getEmployeeType());
            preparedStatement.setString(7, employee.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return employee;
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
