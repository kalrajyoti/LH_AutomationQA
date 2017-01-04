package com.lh.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Reporter;

import com.lh.utils.DbUtils;


public class HealthCoachingDaoImpl implements IHealthCoachingDao{

private static Logger logger = LogManager.getLogger(HealthCoachingDaoImpl.class);


	
	public void resetHealthCoachingForUser(String userName) {
		Connection conn = DbUtils.getMySQLConnection();
		try {
			Statement stmt = conn.createStatement();
			DaoLayer dao = new DaoLayerImpl();

			
			String userid=dao.getUserId(userName);
			String query_HealthCoach = "DELETE FROM ExpertDiscussion WHERE ConversationId in (SELECT ConversationId FROM ExpertConversation where UserId ="+userid+")";
			int noOfRowsDeleted = stmt.executeUpdate(query_HealthCoach);
			Reporter.log("Successfully reset the consent for the user- " + userName);
			Reporter.log("No of rows deleted in Exercise log: " + noOfRowsDeleted);
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}
	
}
