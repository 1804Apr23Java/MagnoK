package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		// This is terrible!!! Don't do it this way!!!
		// Username and password for database
		// Follow this format other than localhost for rds
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // hostname of DB (local host is for local DB) for RDS use
															// url from AWS, xe will be ORCL for RDS
		// String url =
		// "jdbc:oracle:thin:@arn:aws:rds:us-east-2:835471996149:db:mydatabase:1521:ORCL";
		String username = "SYSTEM";
		// String username = "kmagno";
		String password = "Magnificent-24";

		return DriverManager.getConnection(url, username, password);
	}

	// Better!
	// Read in properties from file
	public static Connection getConnectionFromFile(String filename) throws IOException, SQLException {
		Properties prop = new Properties();
		InputStream in = new FileInputStream(filename);
		prop.load(in);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		return DriverManager.getConnection(url, username, password);
	}

}
