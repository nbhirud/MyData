package com.triveni.utility;

import java.sql.*;

//import org.apache.log4j.Logger;

public class JDBCHelper {
	// static Logger log = Logger.getLogger(JDBCHelper.class);
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	/*static final String DB_URL = "jdbc:mysql://localhost:3306/triveni";
	static final String USER = "root";
	static final String PASS = "raya";*/

	static String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	static String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
//	String url = "jdbc:mysql://"+host+":"+port+"/teedix";
	static final String DB_URL = "jdbc:mysql://"+host+":"+port+"/team";
	static final String USER = "adminuituaEP";
	static final String PASS = "IlfXbX5Tvyy1";
	public static Connection connect() {
		Connection connection = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			/*Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Triveni", "triveni", "triveni");*/

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return connection;
	}

}