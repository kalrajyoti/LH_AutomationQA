package com.lh.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Reporter;

import com.lh.utils.DbUtils;

public class OnTargetDaoImpl implements IOnTargetDao{

	private static Logger logger = LogManager.getLogger(OnTargetDaoImpl.class);
	
	public void resetUserOnTargetData(String userName) {
		DaoLayer dao = new DaoLayerImpl();
		String userId=dao.getUserId(userName);
		
		Connection conn = DbUtils.getMySQLConnection();
		try{
			
			Statement stmt = conn.createStatement();
			
			String queryToDeleteUserGamePlanGroupAchievement 			= "delete from OnTarget.UserGamePlanGroupAchievement where userid='" + userId + "'";
			String queryToDeleteUserGamePlanActivityAchievementDetail 	= "delete from onTarget.UserGamePlanActivityAchievementDetail where UserGamePlanActivityAchievementId in (select UserGamePlanActivityAchievementId from onTarget.UserGamePlanActivityAchievement where userid = '" + userId + "')";
			String queryToDeleteUserGamePlanActivityAchievement			= "delete from onTarget.UserGamePlanActivityAchievement where userid = '" + userId + "'";
			String queryToDeleteUserGamePlanActivityAchievementStatus	= "delete from OnTarget.UserGamePlanActivityAchievementStatus where userid = '" + userId + "'";
			String queryToDeleteUserGamePlanAchievement					= "delete from OnTarget.UserGamePlanAchievement where createdby = '" + userId + "'";
			String queryToDeleteUserGamePlan							= "delete from OnTarget.UserGamePlan where userid = '" + userId + "'";
			
			int noOfRowsDeletedUserGamePlanGroupAchievement 			= stmt.executeUpdate(queryToDeleteUserGamePlanGroupAchievement);
			int noOfRowsDeletedUserGamePlanActivityAchievementDetail	= stmt.executeUpdate(queryToDeleteUserGamePlanActivityAchievementDetail);
			int noOfRowsDeletedUserGamePlanActivityAchievement			= stmt.executeUpdate(queryToDeleteUserGamePlanActivityAchievement);
			int noOfRowsDeletedUserGamePlanActivityAchievementStatus	= stmt.executeUpdate(queryToDeleteUserGamePlanActivityAchievementStatus);
			int noOfRowsDeletedUserGamePlanAchievement					= stmt.executeUpdate(queryToDeleteUserGamePlanAchievement);
			int noOfRowsDeletedUserGamePlan								= stmt.executeUpdate(queryToDeleteUserGamePlan);
			
			Reporter.log("No of rows deleted from UserGamePlanGroupAchievement: " + noOfRowsDeletedUserGamePlanGroupAchievement);
			Reporter.log("No of rows deleted from UserGamePlanActivityAchievementDetail: " + noOfRowsDeletedUserGamePlanActivityAchievementDetail);
			Reporter.log("No of rows deleted from UserGamePlanActivityAchievement: " + noOfRowsDeletedUserGamePlanActivityAchievement);
			Reporter.log("No of rows deleted from UserGamePlanActivityAchievementStatus: " + noOfRowsDeletedUserGamePlan);
			Reporter.log("No of rows deleted from UserGamePlanAchievement: " + noOfRowsDeletedUserGamePlanActivityAchievementStatus);
			Reporter.log("No of rows deleted from UserGamePlan: " + noOfRowsDeletedUserGamePlanAchievement);
			
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

			
		}
		
		catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}
}
