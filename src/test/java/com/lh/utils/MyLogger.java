package com.lh.utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * <h2>This class defines a custom logger using Log4j</h2><p>
 * 
 * @author ravi.middha 
 * @version 1.0
 * @since 2014-11-17
 */
public class MyLogger {
	
	public Logger logger = Logger.getLogger("MyLogger");

	public void createLog() {

		FileHandler fh;

		try {

			// This block configure the logger with handler and formatter
			
			//fh = new FileHandler("../logs/LhLogFile.log", true);
			fh = new FileHandler((System.getProperty("user.dir")+"/src/test/resources/LhLogFile.log"), true);
			
			logger.addHandler(fh);
			logger.setLevel(Level.ALL);
			final SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// the following statement is used to log any messages
			logger.log(Level.WARNING, "LH log file");

		} catch (final SecurityException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}
}