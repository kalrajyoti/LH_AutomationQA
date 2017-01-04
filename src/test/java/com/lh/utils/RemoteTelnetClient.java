package com.lh.utils;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;

/**
 * @Description Class is used for establish the telnet connection to QA server
 *              and execute the command on that machine
 * @Prerequisites VPN should be connected on local machine and telnet services
 *                must be started on QA server
 * @author Sanjeev.Kumar
 * 
 * @since 06/04/2016
 */

public class RemoteTelnetClient {
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private String prompt = ">";

	/** Logger to log the Automated Telnet Client Factory log messages. */
	private static Logger logger = LogManager.getLogger(RemoteTelnetClient.class);

	public RemoteTelnetClient(String server, String user, String password) {
		try {
			logger.info("Connect to the specified server.");
			telnet.connect(server, 23);

			logger.info("Get input and output stream references.");
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());

			logger.info("Log the user on.");
			readUntil("login: ");
			write(user);
			readUntil("password: ");
			write(password);

			logger.info("Advance to a prompt.");
			readUntil(prompt);
		} catch (Exception e) {
			logger.error("Connection failed "+e.getMessage());
			e.printStackTrace();
		}
	}

	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			boolean found = false;
			char ch = (char) in.read();
			while (true) {
				System.out.print(ch);
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void write(String value) {
		try {
			out.println(value);
			out.flush();
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String sendCommand(String command) {
		try {
			write(command);
			return readUntil(prompt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) {
		try {
			RemoteTelnetClient telnet = new RemoteTelnetClient("Remote machine IP", "userName", "password");
			System.out.println("Got Connection...");
			telnet.sendCommand("cd C:\\QA1\\ConsoleApps\\ImportProcess");
			System.out.println("run command");
			telnet.sendCommand("LHImportDaemonProcess.exe biometric");
			System.out.println("run command 2");
			telnet.disconnect();
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
