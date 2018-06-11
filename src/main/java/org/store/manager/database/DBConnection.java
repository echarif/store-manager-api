package org.store.manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static Connection connect = null;
	public Statement dbStmt;
	public ResultSet dbRst;

	public static Connection setDataBaseConnection() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/store?"
							+ "user=java&password=java");

		} catch (Exception e) {
			throw e;
		} finally {
		}
		return connect;
	}

	public ResultSet getResultSet(String query, Connection conn) {

		System.out.println(query);
		connect = conn;

		try {
			dbStmt = connect.createStatement();
			dbRst = dbStmt.executeQuery(query);
		} catch (SQLException se) {
			System.out.println(se);
		}
		return dbRst;

	}
}
