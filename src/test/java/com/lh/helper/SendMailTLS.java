package com.lh.helper;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lh.testConfig.TestProperty;

/**
 * <h2>Used for sending emails, using Google's SMTP</h2>
 * <p>
 * 
 * @author amita.arya
 * @version 1.0
 * @since 2014-11-17
 */
public class SendMailTLS {
	private static final String AUTOMATION_RESULTS_SUBJECT = "Automation Results for Today's run";
	/** Logger to log the Driver Factory log messages */
	private static Logger logger = LogManager
			.getLogger(com.lh.helper.SendMailTLS.class);

	/**
	 * Sends an email with test results attached to the email addresses
	 * configured in the config properties.
	 */
	
	private SendMailTLS(){
		logger.debug("Inside the private constructor of the SendMailTLS class");
	}
	
	public static void sendMail() {

		final String username = "lhtest101@gmail.com";
		final String password = "Amita@123";
		String currDir = System.getProperty("user.dir");
		currDir = currDir.substring(0, currDir.length() - 4);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		logger.debug("Creating the session for sending email...");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {
			logger.debug("Creating the multipart message to send...");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("amita.arya@3pillarglobal.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(TestProperty.EMAIL_REPORT_TO)); // Ruby.Ogbolu@livehealthier.com
			message.setSubject(AUTOMATION_RESULTS_SUBJECT);

			MimeBodyPart messageBodyPartAttach = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPartAttach = new MimeBodyPart();
			String filename = ZipResultsFolders.outputZipFile;
			DataSource source = new FileDataSource(filename);
			messageBodyPartAttach.setDataHandler(new DataHandler(source));
			messageBodyPartAttach.setFileName("SuiteResults.zip");

			MimeBodyPart messageBodyPartTxt = new MimeBodyPart();
			String messageTxt = "Dear User,"
					+ "\n\nPlease find the Automation Results Attached with this mail!"
					+ "\nMore detailed results can be found at- " + currDir
					+ "\\" + "testsuite-output  on host- "
					+ InetAddress.getLocalHost().getHostName()
					+ "\n\n- Automation Team";
			messageBodyPartTxt.setText(messageTxt, "utf-8");

			multipart.addBodyPart(messageBodyPartAttach);
			multipart.addBodyPart(messageBodyPartTxt);
			message.setContent(multipart);
			logger.debug("Sending the message.");
			Transport.send(message);
			logger.debug("Successfully sent the message.");

		} catch (MessagingException | UnknownHostException e) {
			logger.error("Exception occured during sending results email.", e);
		}
	}
}