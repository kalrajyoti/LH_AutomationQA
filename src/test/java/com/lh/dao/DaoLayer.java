package com.lh.dao;

/**
 * <h2>This is the interface which should be implemented by the DaoLayerImpl classes</h2>
 * @author amita.arya
 * @version 1.0
 * @since 2015-01-02
 */
public interface DaoLayer {
	
	/**
	 * Used to reset the consent for HA for a user
	 */
	void resetHAForUser(String userName);
	
	void resetNurturHAForUser(String UserName);
	
	void delHAReportDataForUser(String userName);
	
	void resetSelfRegistration(String externalId);
	
	void updateHAEventToCurrentDate();
	
	void resetUserHAEvent(String userName);
	
	void deleteIncentiveAchievement(String userName, String Incentive);
	
	void deleteAttestationRecord(String userName);
	
	void deleteIncentiveGroupAchievement(String userName, String incentiveGroup);
	
	String getUserId(String userName);
	
	void resetUserOnTrackData();

	String retrieveOnTrackData(String parameter);

	String retrieveOnTrackThemeDate(int rowNo);

}
