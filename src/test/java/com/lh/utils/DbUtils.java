package com.lh.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <h2>Utility Class for creating and closing database connections.</h2>
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-02
 */
public class DbUtils {

	/** Logger to log the Driver Factory log messages. */
	private static Logger logger = LogManager.getLogger(DbUtils.class);

	/**
	 * Used for creating DB connections
	 * @return connection connection object
	 */
	public static Connection getMySQLConnection() {
		Connection conn = null;
		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("net.sourceforge.jtds.jdbc.Driver");

            //String connectionUrl = "jdbc:sqlserver://10.177.141.160;database=NewPortal;user=amita;password=nVog85hLIHxP1fdNZgHg";
			String connectionUrl = "jdbc:jtds:sqlserver://10.177.141.160;DatabaseName=NewPortal;user=PranaliJain;password=Password_2017";

			//jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
			conn = DriverManager.getConnection(connectionUrl);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		logger.info("Created database connection successfully.");
		return conn;
	}

	/**
	 * Used for releasing/closing DB connection
	 * @param conn connection object
	 */
	public static void releaseMySQLConnection(Connection conn) {
		try {
			if (conn != null) {
				logger.info("Closing the database connection...");
				conn.close();
			}

		} catch (Exception e) {
			logger.info("Encountered exception while closing the connection...", e);
		} finally {
			logger.info("Executing the finally of releaseMySQLConnection...");
		}
	}

	/**
	 * Used for releasing SQL statement
	 * @param stmt Statement object
	 */
	public static void releaseMySQLStatement(Statement stmt) {
		try {
			if (stmt != null) {
				logger.info("Closing the sql statement...");
				stmt.close();
			}
		} catch (Exception e) {
			logger.info("Encountered exception while closing the sql connection...", e);
		} finally {
			logger.info("Executing the finally of releaseMySQLStatement...");
		}
	}
}
