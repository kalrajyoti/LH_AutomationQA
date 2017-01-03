package com.lh.dao;

import com.lh.utils.DateUtil;
import com.lh.utils.DbUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * <h2>Contains the methods to interact with the database</h2>
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-02
 */
public class DaoLayerImpl implements DaoLayer{
	/** Logger for the Login class */
	private static Logger logger = LogManager.getLogger(DaoLayerImpl.class);

	/**
	 * Used to completely reset the HA and Consent and Data for a user.
	 *
	 * @param userName the name of the user for which the HA needs to be reset
	 */
	public void resetHAForUser(String userName) {
		Connection conn = DbUtils.getMySQLConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "Delete from PSQuestionAnswer where userid = (select userid from [user] where username = '" + userName +"') and seasonid =1100";
			int noOfRowsDeleted = stmt.executeUpdate(query);
			logger.info("Successfully deleted AnsID and Consent for the user: " + userName);
			logger.info("No of rows deleted : " + noOfRowsDeleted);
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}

	public void resetNurturHAForUser(String userName) {
		Connection conn = DbUtils.getMySQLConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "UPDATE TOP (1) [NewPortal].[dbo].[PSQuestionAnswer]\n" +
					" SET \n" +
					"\tqaxml=null , IsQuestionnaireCompleted=0 , IsAuthorized=0 , IsConsentGiven=0 , \n" +
					"\tQuestionnaireCompletedDate=null , [Status]='authinprogress', ReportStatus=null , ExternalReportID=null\n" +
					" WHERE AnsID =\n" +
					" (SELECT Top (1) AnsID from [NewPortal].[dbo].[PSQuestionAnswer]\n" +
					" WHERE UserID = \n" +
					" (SELECT UserID from [NewPortal].[dbo].[user]\n" +
					" WHERE Username = '"+userName+"') ORDER BY [ModifiedDate] desc)";
			int noOfRowsDeleted = stmt.executeUpdate(query);
			logger.info("Successfully deleted AnsID and Consent for the user: " + userName);
			logger.info("No of rows deleted : " + noOfRowsDeleted);
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}

	public void delHAReportDataForUser(String userName) {
		Connection conn = DbUtils.getMySQLConnection();
		try {
			Statement stmt = conn.createStatement();

			String queryToDeletePSQReportData = "Delete from PSQReportData where psqreportId in (select psqreportId from PSQReport where userid= (select userid from [user] where username = 'TestUserEST04'))";
			String queryToDeletePSQReport = "Delete from PSQReport where  userid= (select userid from [user] where username = 'TestUserEST04')";
			String queryToResetHA = "Delete from PSQuestionAnswer where userid = (select userid from [user] where username = '" + userName +"') and seasonid =1100";

			int noOfRowsDeletedPSQReportData = stmt.executeUpdate(queryToDeletePSQReportData);
			int noOfRowsDeletedPSQReport = stmt.executeUpdate(queryToDeletePSQReport);
			int noOfRowsDeletedPSQuestionAnswer = stmt.executeUpdate(queryToResetHA);

			Reporter.log("Successfully reset the consent for the user- " + userName);
			Reporter.log("No of rows deleted from PSQReportData: " + noOfRowsDeletedPSQReportData);
			Reporter.log("No of rows deleted from PSQReport: " + noOfRowsDeletedPSQReport);
			Reporter.log("No of rows deleted from PSQuestionAnswer: " + noOfRowsDeletedPSQuestionAnswer);

			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);

		}
	}

	@Override
	public void resetSelfRegistration(String externalId) {

		Connection conn = DbUtils.getMySQLConnection();

		try {
			Statement stmt = conn.createStatement();

			String queryToUpdateUserRegisteration = "Update [user] set IsFirstTimeLoggedIn=0 where externalid='" + externalId.trim() + "'";

			int noOfRowsupdated = stmt.executeUpdate(queryToUpdateUserRegisteration);

			Reporter.log("Successfully updated the IsFirstTimeLoggedIn flag to false for - " + externalId);
			Reporter.log("No of rows updated in the user table are - " + noOfRowsupdated);

			System.out.println("Successfully updated the IsFirstTimeLoggedIn flag to false for - " + externalId);
			System.out.println("No of rows updated in the user table are - " + noOfRowsupdated);

			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {

			logger.error("Found an SQLException, unable to connect to the database", e);

		}


	}

	@Override
	public void updateHAEventToCurrentDate() {
		Connection conn = DbUtils.getMySQLConnection();

		try {
			Statement stmt = conn.createStatement();

			String todaysDate = DateUtil.getFormattedCurrentDate();

			String queryToUpdateHAEventDate = "Update screening_event set EventDate='" + todaysDate + "' where EventId = 529";

			int noOfRowsupdated = stmt.executeUpdate(queryToUpdateHAEventDate);

			Reporter.log("Successfully updated the EventDate to todays date " + todaysDate);
			Reporter.log("No of rows updated in the user table are - " + noOfRowsupdated);

			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {

			logger.error("Found an SQLException, unable to connect to the database", e);

		}

	} 


	/**
	 * Used to reset the IncentiveAchievement data of user for a Incentive
	 * @param userName the name of the user
	 */
	//@Override
	public void deleteIncentiveAchievement(String userName, String Incentive) {
		Connection conn = DbUtils.getMySQLConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "delete dbo.IncentiveAchievement where IncentiveId = (select IncentiveID from Incentive where ShortName = '" + Incentive + "') and userid = (select userid from [user] where username = '" + userName + "')";
			//String queryToDeleteIncentiveAchievement = "delete dbo.IncentiveAchievement where IncentiveId = (select IncentiveID from Incentive where ShortName = '" + Incentive + "') and userid = (select userid from [user] where username = '" + userName + "')";

			int noOfRowsDeleteIncentiveAchievement = stmt.executeUpdate(query);
			logger.info("Successfully reset the Incentive Achievement for the user- " + userName);
			logger.info("No of rows deleted from IncentiveAchievement table: " + noOfRowsDeleteIncentiveAchievement);
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);
		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}

	/**
	 * Used to reset the AttestationRecord of user 
	 * @param userName the name of the user
	 */
	@Override
	public void deleteAttestationRecord(String userName) {

		Connection conn = DbUtils.getMySQLConnection();

		try {
			Statement stmt = conn.createStatement();

			String queryToDeleteAttestationRecord= "delete AttestationRecord where userid = (select userid from [user] where username = '" + userName + "')";

			int noOfRowsOnDeleteAttestationRecord = stmt.executeUpdate(queryToDeleteAttestationRecord);

			Reporter.log("Successfully reset the Attestation Record for the user- " + userName);
			Reporter.log("No of rows deleted from AttestationRecord table: " + noOfRowsOnDeleteAttestationRecord);

			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}

	}

	/**
	 * Used to reset the IncentiveGroupAchievement data of user for a IncentiveGroup
	 * @param userName the name of the user
	 */
	@Override
	public void deleteIncentiveGroupAchievement(String userName, String incentiveGroup) {
		Connection conn = DbUtils.getMySQLConnection();

		try {
			Statement stmt = conn.createStatement();

			String queryToDeleteIncentiveGroupAchievement = "delete IncentiveGroupAchievement where UserID = (select UserID from [user] where username ='" + userName + "') and IGID = (select IGID from IncentiveGroup where GroupName ='" + incentiveGroup + "')";

			int noOfRowsOnDeleteIncentiveGroupAchievement =  stmt.executeUpdate(queryToDeleteIncentiveGroupAchievement);


			Reporter.log("Successfully reset the Incentive Group Achievement for the user- " + userName);
			Reporter.log("No of rows deleted from IncentiveGroupAchievement table: " + noOfRowsOnDeleteIncentiveGroupAchievement);

			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}

	}


	public void resetUserHAEvent(String userName) {

		Connection conn = DbUtils.getMySQLConnection();

		try {
			Statement stmt = conn.createStatement();

			String queryToResetUserEventRegisteration = "Update screening_event_slot set UserId = NULL where UserId=  (select UserID from [user] where username ='" + userName + "')";

			int noOfRowsOnResetUserEventRegisteration =  stmt.executeUpdate(queryToResetUserEventRegisteration);

			System.out.println("Successfully reset the Event registeraion for the user- " + userName);
			System.out.println("No of rows deleted from screening_event_slot table: " + noOfRowsOnResetUserEventRegisteration);

			Reporter.log("Successfully reset the Event registeraion for the user- " + userName);
			Reporter.log("No of rows deleted from screening_event_slot table: " + noOfRowsOnResetUserEventRegisteration);

			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}

	public String getUserId(String userName){

		Connection conn = DbUtils.getMySQLConnection();
		String userId = "";
		try{
			Statement stmt = conn.createStatement();

			String queryToGetUserId = "Select userId from [user] where userName='" + userName + "'";

			ResultSet rs = stmt.executeQuery(queryToGetUserId);

			while(rs.next()){
				userId = rs.getString(1);
				System.out.println("userID: " + rs.getString(1));
			
				Reporter.log("Successfully got the userId of a user- " + userName);
				
			}
			
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);


		} catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
		return userId;
	}
	
	@Override
	public void resetUserOnTrackData() {
		
		//String userId = getUserId(userName);
		
		Connection conn = DbUtils.getMySQLConnection();
		try{
			
			Statement stmt = conn.createStatement();
			
			String queryToDeleteOnTrackUserData 			= "delete FROM ChallengeRegistration where UserId=432267";
			
			int noOfRowsDeletedUser 			= stmt.executeUpdate(queryToDeleteOnTrackUserData);
			
			Reporter.log("No of rows deleted from UserGamePlanGroupAchievement: " + noOfRowsDeletedUser);
			
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

			
		}
		
		catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
	}
	@Override
	public String retrieveOnTrackData(String parameter) {
		
		String text = " ";
		String extraChar = "";
		if(parameter.endsWith("BannerTextNotRegistered")){
			extraChar="#";
		}
		Connection conn = DbUtils.getMySQLConnection();
		try{
			
			Statement stmt = conn.createStatement();
			
			String queryTogetOnTrackText 			= "SELECT Top 1 SUBSTRING(ContentText,CHARINDEX('<p>',ContentText),CHARINDEX('"+extraChar+"</p>',ContentText)+1) as ContentTxt FROM [NewPortal].[dbo].[ContentItem] WHERE Title = '"+parameter+"'";
			System.out.println(queryTogetOnTrackText);
			ResultSet onTrackDataText 			= stmt.executeQuery(queryTogetOnTrackText);
			
			while(onTrackDataText.next()){
				text = onTrackDataText.getString(1).toString();
		//		System.out.println("banner text " + onTrackDataText.getString(1));
				String[] edtxt = onTrackDataText.getString(1).split("</p>");
				System.out.println(edtxt[1]);
				Reporter.log("banner text " + onTrackDataText.getString(1));
				
			}
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

			
		}
		
		catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
		return text;
	}
	
	@Override
	public String retrieveOnTrackThemeDate(int rowNo) {
		
		String text = " ";
		
		Connection conn = DbUtils.getMySQLConnection();
		try{
			
			Statement stmt = conn.createStatement(
				    ResultSet.TYPE_SCROLL_INSENSITIVE, 
				    ResultSet.CONCUR_READ_ONLY);
			
			String queryTogetOnTrackText 			= "Select CONVERT(VARCHAR,dateadd(dd,ott.StartDateOffset/864000000000,cc.StartDate), 101) as themestartdate "
                                                      +"from OnTrack ot join OnTrackTheme ott on " 
                                                      +"ot.OnTrackId= ott.OnTrackId JOIN ClientCampaignsProductMapping ccp on "
                                                      +"ccp.OnTrackId=ot.OnTrackId join ClientCampaigns cc on " 
                                                      +"ccp.ClientCampaignID = cc.ClientCampaignId JOIN ClientAccount ca on "
                                                      +"ca.ClientAccountID = cc.ClientAccountID join Challenges ch on "
                                                      +"ott.ChallengeId=ch.ChallengeId "
                                                      +"Where ca.clientaccountname = 'Expedia'";
			
			ResultSet onTrackDataText 			= stmt.executeQuery(queryTogetOnTrackText);
		             onTrackDataText.first();
		//	while(onTrackDataText.next()){
				
				onTrackDataText.absolute(rowNo);	
				text = onTrackDataText.getString(1);
				System.out.println("banner text " + onTrackDataText.getString(1));
			
				Reporter.log("banner text " + onTrackDataText.getString(1));
				
	//		}
			DbUtils.releaseMySQLStatement(stmt);
			DbUtils.releaseMySQLConnection(conn);

			
		}
		
		catch (SQLException e) {
			logger.error("Found an SQLException, unable to connect to the database", e);
		}
		return text;
	}

	public static void main(String args[]) {
		DaoLayer daoObj = new DaoLayerImpl();

		System.out.println("created the connection");
//		daoObj.resetUserHAEvent("TestUserEst05");
//		daoObj.delHAReportDataForUser("TestUserEST04");
		daoObj.resetHAForUser("TestUserEST01");
		daoObj = null;

	}

	

}
