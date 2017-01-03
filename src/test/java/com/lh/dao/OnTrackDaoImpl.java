package com.lh.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

import com.lh.utils.DbUtils;

public class OnTrackDaoImpl implements IOnTrackDao {

private static Logger logger = LogManager.getLogger(OnTrackDaoImpl.class);
	
	public void resetonTrackForUser(String userName) {
		Connection conn = DbUtils.getMySQLConnection();
		try {
			Statement stmt = conn.createStatement();
			DaoLayer dao = new DaoLayerImpl();
			String userid=dao.getUserId(userName);
			String query_ChallengeRegistration = "delete from challengeregistration where userid="+userid+"";
			String query_Survey = "delete from ontrackuserachievement where userid="+userid+"";
			int noOfRowsDeleted = stmt.executeUpdate(query_ChallengeRegistration);
			int noOfRowsDeletedSurvey = stmt.executeUpdate(query_Survey);
			Reporter.log("Successfully reset the challenge registrationa and survey for the user- " + userName);
			Reporter.log("No of rows deleted in ChallengeRegistration: " + noOfRowsDeleted);
			Reporter.log("No of rows deleted in Survey log: " + noOfRowsDeletedSurvey);
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}
	
	public static void main(String a[]){
		OnTrackDaoImpl lf = new OnTrackDaoImpl();
		lf.resetonTrackForUser("AkashTestUser1");
	}
	
	
}
