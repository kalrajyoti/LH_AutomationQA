package com.lh.utils.jyoti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PropertyFileReaderModified 
{

	private static Logger logger = LogManager.getLogger(com.lh.utils.PropertyFileReader.class);
	public String returnPropVal(final String propertyFileName, final String key) {

		// get a new properties object:
		final Properties properties = new Properties();
		String value = null;
		{
			try {
				
				//properties.load(new FileInputStream(new File("../config/"+ propertyFileName + ".properties")));
				properties.load(new FileInputStream(new File(System.getProperty("user.dir")+ "/src/test/resources/automation.properties")));
				

				// get PROPERTY value based on key:
				value = properties.getProperty(key);

			}
			catch (final FileNotFoundException e) {
				logger.error("The file was not found at "+"/src/test/resouces"+ propertyFileName + ".properties", e);

			} 
			catch (final IOException e) {
				logger.error("IOException was found in returnPropVal method", e);
			}
			return value;
		}
}}
