package com.lh.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Reporter;

import com.lh.utils.DbUtils;

public class LifestyleManagerDaoImpl implements ILifestyleManagerDao {

	private static Logger logger = LogManager.getLogger(LifestyleManagerDaoImpl.class);
	
	public void resetLifestyleManagerForUser(String userName) {
		Connection conn = DbUtils.getMySQLConnection();
		try {
			Statement stmt = conn.createStatement();
			DaoLayer dao = new DaoLayerImpl();
			String userid=dao.getUserId(userName);
			String query_Exercise = "delete from ExerciseLogApprovals where ExerciseLogId in (select exerciseid from exercise_log where userid='"+userid+"')";
			String query_Exercise2 = "delete from exercise_log where userid="+userid+"";
			String query_Sleep = "delete from sleep_log where userid='"+userid+"'";
			int noOfRowsDeleted = stmt.executeUpdate(query_Exercise);
			int noOfRowsDeletedExerciseLog = stmt.executeUpdate(query_Exercise2);
			int noOfDeletedInSleepLog = stmt.executeUpdate(query_Sleep);
			Reporter.log("Successfully reset the consent for the user- " + userName);
			Reporter.log("No of rows deleted in ExerciseLogApprovals: " + noOfRowsDeleted);
			Reporter.log("No of rows deleted in Exercise log: " + noOfRowsDeletedExerciseLog);
			Reporter.log("No of rows deleted in Sleep log: " + noOfDeletedInSleepLog);
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}
	
	public static void main(String a[]){
		LifestyleManagerDaoImpl lf = new LifestyleManagerDaoImpl();
		lf.resetLifestyleManagerForUser("TestUserEST01");
	}
}
