package com.lh.pages.authenticated.ha;

import static com.lh.helper.DriverFactory.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;

import com.lh.utils.MyLogger;

/**
 * Created by pranali.jain on 10/17/2016 for LiveHealthier-EnvolvePeopleCare
 * <p>
 * Summary - Client: Exelis
 */
@Listeners(com.lh.helper.LHTestListener.class)
public class NurturWellbeingEPCFinancialPage extends HealthAssessmentPage{

	 /**
     * Element Locators
     */
	By wellbeingSectionText                                             = By.xpath("//*[contains(.,'about your wellbeing')][not(.//*[contains(., 'about your wellbeing')])]");
	By monthlyBillsPersonalFinanceChkbox								= By.id("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_checkboxList_0");
	By unexpectedExpensesPersonalFinanceChkbox							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_checkboxList_1");
	By creditCardDebtPersonalFinanceChkbox								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_checkboxList_2");
	By retirementSavingPersonalFinanceChkbox							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_checkboxList_3");
	By savingEducationPersonalFinanceChkbox								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_checkboxList_4");
	By noneOfTheAboveersonalFinanceChkbox								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl00_HAQuestionCtrl1_ctl00_checkboxList_5");

	By noChangeNeededAddressingFinanceBtn								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_0");
	By startedMakingChangesAddressingFinanceBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_1");
	By planToChangeWithinAMonthAddressingFinanceBtn						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_2");
	By planToChangeIn6MonthsAddressingFinanceBtn						= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_3");
	By noPlansToChangeAddressingFinanceBtn								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl01_HAQuestionCtrl1_ctl00_rbtList_4");
	
	By hoursSleepField													= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl02_HAQuestionCtrl1_ctl00_TheNumericTextBox");
	
    By neverNoControlImportantThingsBtn                                 = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_0");
    By almostNeverNoControlImportantThingsBtn                           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_1");
    By sometimesNoControlImportantThingsBtn                             = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_2");
    By fairlyOftenNoControlImportantThingsBtn                           = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_3");
    By veryOftenNoControlImportantThingsBtn                             = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl03_HAQuestionCtrl1_ctl00_rbtList_4");
    
    By neverInabilityHandleProblemsBtn                                  = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_0");
    By almostNeverInabilityHandleProblemsBtn                            = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_1");
    By sometimesInabilityHandleProblemsBtn                              = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_2");
    By fairlyOftenInabilityHandleProblemsBtn                            = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_3");
    By veryOftenInabilityHandleProblemsBtn                              = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl04_HAQuestionCtrl1_ctl00_rbtList_4");

    By neverFeelThingsGoingMyWayBtn                                     = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_0");
    By almostNeverFeelThingsGoingMyWayBtn                               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_1");
    By sometimesFeelThingsGoingMyWayBtn                                 = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_2");
    By fairlyOftenFeelThingsGoingMyWayBtn                               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_3");
    By veryOftenFeelThingsGoingMyWayBtn                                 = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl05_HAQuestionCtrl1_ctl00_rbtList_4");
    
    By neverCouldNotOvercomeDifficultiesBtn                             = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_0");
    By almostNeverCouldNotOvercomeDifficultiesBtn                       = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_1");
    By sometimesCouldNotOvercomeDifficultiesBtn                         = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_2");
    By fairlyOftenCouldNotOvercomeDifficultiesBtn                       = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_3");
    By veryOftenCouldNotOvercomeDifficultiesBtn                         = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl06_HAQuestionCtrl1_ctl00_rbtList_4");

    By noChangeIsNeededManagingStressBtn                                = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_0");
    By maintainedChangesForSixPlusMonthsManagingStressBtn               = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_1");
    By startedMakingChangesAlreadyManagingStressBtn                     = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_2");
    By planToChangeInNextMonthManagingStressBtn                         = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_3");
    By planToChangeInNextSixMonthsManagingStressBtn                     = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_4");
    By noPlansToChangeManagingStressBtn                                 = By.cssSelector("#ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl07_HAQuestionCtrl1_ctl00_rbtList_5");
    
    By noLittleInterestNorPleasure                                      = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_dvTwoChoiceyesno51_dvTwo']/table/tbody/tr/td[2]/input");
    By yesLittleInterestAndPleasure                                     = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl08_HAQuestionCtrl1_dvTwoChoiceyesno51_dvTwo']/table/tbody/tr/td[1]/input");

    By noFeelingDownDepressedNorHopeless                                = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_dvTwoChoiceyesno53_dvTwo']/table/tbody/tr/td[2]/input");
    By yesFeelingDownDepressedNorHopeless                               = By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl10_HAQuestionCtrl1_dvTwoChoiceyesno53_dvTwo']/table/tbody/tr/td[1]/input");
    
    By noCurrentlyEmployedStatus										= By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl18_HAQuestionCtrl1_dvTwoChoiceyesno61_dvTwo']/table/tbody/tr/td[2]/input");
    By yesCurrentlyEmployedStatus										= By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl18_HAQuestionCtrl1_dvTwoChoiceyesno61_dvTwo']/table/tbody/tr/td[1]/input");
    
    By zeroHealthAffectingRegularActivityBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_0");
    By oneHealthAffectingRegularActivityBtn								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_1");
    By twoHealthAffectingRegularActivityBtn								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_2");
    By threeHealthAffectingRegularActivityBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_3");
    By fourHealthAffectingRegularActivityBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_4");
    By fiveHealthAffectingRegularActivityBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_5");
    By sixHealthAffectingRegularActivityBtn								= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_6");
    By sevenHealthAffectingRegularActivityBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_7");
    By eightHealthAffectingRegularActivityBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_8");
    By nineHealthAffectingRegularActivityBtn							= By.id("ctl00_ContentPlaceHolder1_PSQControl1_rptRepeater_ctl23_HAQuestionCtrl1_ctl00_rbtList_9");
    
    /**
     * Other Class Initiators
     */
    private static Logger logger = LogManager.getLogger(com.lh.pages.authenticated.ha.NurturWellbeingHAPage.class);

    /**
     * Constructor
     */
    public NurturWellbeingEPCFinancialPage(){
        super();
        logger.info("...initializing the Wellbeing Nurtur HA Page Object");
        // Check that we're on the right page.
        if (!(driver.findElement(wellbeingSectionText).isDisplayed())) {
            logger.error("This is not the Wellbeing Nurtur Health Assessment page.");
            throw new IllegalStateException(
                    "This is not the Wellbeing Nurtur Health Assessment page.");
        }
        logger.info("INITIALIZED the Wellbeing Nurtur HA Page Object.");
    }

    //=========================
    //METHODS
    //=========================
    
    public void setPersonalFinances(String personalFinance){
    	personalFinance = personalFinance.toLowerCase().trim();
    	switch(personalFinance){
    	case "my monthly bills" 				: driver.findElement(monthlyBillsPersonalFinanceChkbox).click();
    		logger.info("Things affecting the Personal finances -- "+ personalFinance);
    		break;
    	case "unexpected expenses"				: driver.findElement(unexpectedExpensesPersonalFinanceChkbox).click();
    		logger.info("Things affecting the Personal finances -- "+ personalFinance);
    		break;
    	case "credit card debt" 				: driver.findElement(creditCardDebtPersonalFinanceChkbox).click();
    		logger.info("Things affecting the Personal finances -- "+ personalFinance);
    		break;
    	case "my retirement savings" 			: driver.findElement(retirementSavingPersonalFinanceChkbox).click();
    		logger.info("Things affecting the Personal finances -- "+ personalFinance);	
    		break;
    	case "saving for education / college" 	: driver.findElement(savingEducationPersonalFinanceChkbox).click();
    		logger.info("Things affecting the Personal finances -- "+ personalFinance);
    		break;
    	case "none of the above" 				: driver.findElement(noneOfTheAboveersonalFinanceChkbox).click();
    		logger.info("Things affecting the Personal finances -- "+ personalFinance);
    		break;
    	default : 
    		logger.error("INVALID PARAMETER-- Set to 'My monthly bills','Unexpected expenses','Credit card debt','My retirement savings','Saving for education / college','None of the above'");
    	}
    }
    
    public void setPlansForAddressingFinance(String planToAddressFinance){
    	planToAddressFinance = planToAddressFinance.toLowerCase().trim();
    	switch(planToAddressFinance){
    	case "no change is needed" : driver.findElement(noChangeNeededAddressingFinanceBtn).click();
    		logger.info("Plans for addressing finance set to -- " + planToAddressFinance);
    		break;
    	case "started making changes already" : driver.findElement(startedMakingChangesAddressingFinanceBtn).click();
    		logger.info("Plans for addressing finance set to -- " + planToAddressFinance);
    		break;
    	case "plan to change within the month" : driver.findElement(planToChangeWithinAMonthAddressingFinanceBtn).click();
    		logger.info("Plans for addressing finance set to -- " + planToAddressFinance);
    		break;
    	case "plan to change in the next 6 months" : driver.findElement(planToChangeIn6MonthsAddressingFinanceBtn).click();
    		logger.info("Plans for addressing finance set to -- " + planToAddressFinance);
    		break;
    	case "no plans to change" : driver.findElement(noPlansToChangeAddressingFinanceBtn).click();
    		logger.info("Plans for addressing finance set to -- " + planToAddressFinance);
    		break;
    	default	:
    		logger.error("INVALID PARAMETER -- Set to 'No change is needed','Started making changes already','Plan to change within the month','Plan to change in the next 6 months','No plans to change'");
    	}
    }
    
    public void setHoursOfSleepDaily(String numericHours){
        driver.findElement(hoursSleepField).clear();
        driver.findElement(hoursSleepField).sendKeys(numericHours);

        logger.info("Hours of Sleep set to -- " +numericHours);
    }

    public void setLittleInterestPleasure(String ans){
        ans = ans.trim().toLowerCase();
        switch(ans){
            case "no" : driver.findElement(noLittleInterestNorPleasure).click();
                logger.info("Set NO -- bothered by little interest or pleasure in things?");
                break;
            case "yes" : driver.findElement(yesLittleInterestAndPleasure).click();
                logger.info("Set YES -- bothered by little interest or pleasure in things?");
                break;
            default :
                logger.error("INVALID PARAMETER--Set to: 'yes' or 'no'.");
        }

    }

    public void setFeelingDownDepressedHopeless(String ans){
        ans = ans.trim().toLowerCase();
        switch(ans){
            case "no" : driver.findElement(noFeelingDownDepressedNorHopeless).click();
                logger.info("Set NO -- bothered by feeling down, depressed, or hopeless?");
                break;
            case "yes" : driver.findElement(yesFeelingDownDepressedNorHopeless).click();
                logger.info("Set YES -- bothered by feeling down, depressed, or hopeless?");
                break;
            default :
                logger.error("INVALID PARAMETER--Set to: 'yes' or 'no'.");
        }
    }

    public void setPlansForManagingStress(String plan){
        plan = plan.trim().toLowerCase();
        switch(plan){
            case "no change is needed" : driver.findElement(noChangeIsNeededManagingStressBtn).click();
                logger.info("Plans for MANAGING STRESS set to - " +plan);
                break;
            case "maintained changes for 6+ months" : driver.findElement(maintainedChangesForSixPlusMonthsManagingStressBtn).click();
                logger.info("Plans for MANAGING STRESS set to - " +plan);
                break;
            case "started making changes already" : driver.findElement(startedMakingChangesAlreadyManagingStressBtn).click();
                logger.info("Plans for MANAGING STRESS set to - " +plan);
                break;
            case "plan to change in next month" : driver.findElement(planToChangeInNextMonthManagingStressBtn).click();
                logger.info("Plans for MANAGING STRESS set to - " +plan);
                break;
            case "plan to change in next 6 months" : driver.findElement(planToChangeInNextSixMonthsManagingStressBtn).click();
                logger.info("Plans for MANAGING STRESS set to - " +plan);
                break;
            case "no plans to change" : driver.findElement(noPlansToChangeManagingStressBtn).click();
                logger.info("Plans for MANAGING STRESS set to - " +plan);
                break;
            default :
                logger.error("INVALID PARAMETER--Set to: 'No change is needed','Maintained changes for 6+ months','Started making changes already','Plan to change in next month','Plan to change in next 6 months','No plans to change'.");
        }
    }

    public void setFeltUnableToControlImportantThingsQ(String answer){
        answer = answer.trim().toLowerCase();
        switch(answer){
            case "never" : driver.findElement(neverNoControlImportantThingsBtn).click();
                logger.info("Question: Unable to control important things set to - " +answer);
                break;
            case "almost never" : driver.findElement(almostNeverNoControlImportantThingsBtn).click();
                logger.info("Question: Unable to control important things set to - " +answer);
                break;
            case "sometimes" : driver.findElement(sometimesNoControlImportantThingsBtn).click();
                logger.info("Question: Unable to control important things set to - " +answer);
                break;
            case "fairly often" : driver.findElement(fairlyOftenNoControlImportantThingsBtn).click();
                logger.info("Question: Unable to control important things set to - " +answer);
                break;
            case "very often" : driver.findElement(veryOftenNoControlImportantThingsBtn).click();
                logger.info("Question: Unable to control important things set to - " +answer);
                break;
            default :
                logger.error("INVALID PARAMETER--Set to: 'never','almost never','sometimes','fairly often','very often'.");
        }
    }

    public void setFeltConfidentPersonalProblemsQ(String answer){
        answer = answer.trim().toLowerCase();
        switch(answer){
            case "never" : driver.findElement(neverInabilityHandleProblemsBtn).click();
                logger.info("Question: Often felt confident about handling personal problems - " +answer);
                break;
            case "almost never" : driver.findElement(almostNeverInabilityHandleProblemsBtn).click();
                logger.info("Question: Often felt confident about handling personal problems - " +answer);
                break;
            case "sometimes" : driver.findElement(sometimesInabilityHandleProblemsBtn).click();
                logger.info("Question: Often felt confident about handling personal problems - " +answer);
                break;
            case "fairly often" : driver.findElement(fairlyOftenInabilityHandleProblemsBtn).click();
                logger.info("Question: Often felt confident about handling personal problems - " +answer);
                break;
            case "very often" : driver.findElement(veryOftenInabilityHandleProblemsBtn).click();
                logger.info("Question: Often felt confident about handling personal problems - " +answer);
                break;
            default :
                logger.error("INVALID PARAMETER--Set to: 'never','almost never','sometimes','fairly often','very often'.");
        }
    }
    public void setFeltThingsGoingYourWayQ(String answer){
        answer = answer.trim().toLowerCase();
        switch(answer){
            case "never" : driver.findElement(neverFeelThingsGoingMyWayBtn).click();
                logger.info("Question: Often felt that things were going your way - " +answer);
                break;
            case "almost never" : driver.findElement(almostNeverFeelThingsGoingMyWayBtn).click();
                logger.info("Question: Often felt that things were going your way - " +answer);
                break;
            case "sometimes" : driver.findElement(sometimesFeelThingsGoingMyWayBtn).click();
                logger.info("Question: Often felt that things were going your way - " +answer);
                break;
            case "fairly often" : driver.findElement(fairlyOftenFeelThingsGoingMyWayBtn).click();
                logger.info("Question: Often felt that things were going your way - " +answer);
                break;
            case "very often" : driver.findElement(veryOftenFeelThingsGoingMyWayBtn).click();
                logger.info("Question: Often felt that things were going your way - " +answer);
                break;
            default :
                logger.error("INVALID PARAMETER--Set to: 'never','almost never','sometimes','fairly often','very often'.");
        }
    }
    public void setFeltDifficultiesPilingUpHighQ(String answer){
        answer = answer.trim().toLowerCase();
        switch(answer){
            case "never" : driver.findElement(neverCouldNotOvercomeDifficultiesBtn).click();
                logger.info("Question: Felt difficulties were piling up so high - " +answer);
                break;
            case "almost never" : driver.findElement(almostNeverCouldNotOvercomeDifficultiesBtn).click();
                logger.info("Question: Felt difficulties were piling up so high - " +answer);
                break;
            case "sometimes" : driver.findElement(sometimesCouldNotOvercomeDifficultiesBtn).click();
                logger.info("Question: Felt difficulties were piling up so high - " +answer);
                break;
            case "fairly often" : driver.findElement(fairlyOftenCouldNotOvercomeDifficultiesBtn).click();
                logger.info("Question: Felt difficulties were piling up so high - " +answer);
                break;
            case "very often" : driver.findElement(veryOftenCouldNotOvercomeDifficultiesBtn).click();
                logger.info("Question: Felt difficulties were piling up so high - " +answer);
                break;
            default :
                logger.error("INVALID PARAMETER--Set to: 'never','almost never','sometimes','fairly often','very often'.");
        }
    }
    
    public void setCurrentlyEmployedStatus(String currentEmploymentStatus){
    	currentEmploymentStatus = currentEmploymentStatus.toLowerCase().trim();
    	switch(currentEmploymentStatus){
    	case "yes" : driver.findElement(yesCurrentlyEmployedStatus).click();
    		logger.info("Current employed status set to -- " + currentEmploymentStatus);
    		break;
    	case "no" : driver.findElement(noCurrentlyEmployedStatus).click();
    		logger.info("Current employed status set to -- " + currentEmploymentStatus);
    		break;
    	default :
    		logger.error("INVALID PARAMETER -- Set to: 'Yes', 'No'");
    	}
    }
    
    public void setHealthProblemAffectingRegularActivity(String healthaffectingRegularActivity){
    	healthaffectingRegularActivity = healthaffectingRegularActivity.toLowerCase().trim();
    	switch (healthaffectingRegularActivity){
    	case "0" : driver.findElement(zeroHealthAffectingRegularActivityBtn).click();
    		break;
		case "1" : driver.findElement(oneHealthAffectingRegularActivityBtn).click();
			break;
		case "2" : driver.findElement(twoHealthAffectingRegularActivityBtn).click();
			break;
		case "3" : driver.findElement(threeHealthAffectingRegularActivityBtn).click();
			break;
		case "4" : driver.findElement(fourHealthAffectingRegularActivityBtn).click();
			break;
		case "5" : driver.findElement(fiveHealthAffectingRegularActivityBtn).click();
			break;
		case "6" : driver.findElement(sixHealthAffectingRegularActivityBtn).click();
			break;
		case "7" : driver.findElement(sevenHealthAffectingRegularActivityBtn).click();
			break;
		case "8" : driver.findElement(eightHealthAffectingRegularActivityBtn).click();
			break;
		case "9" : driver.findElement(nineHealthAffectingRegularActivityBtn).click();
			break;
		default:
			logger.error("INVALID PARAMETER, choose option between 0-9");
		}
    }

}
